package com.clothesshop.backend.services.impl;


import com.clothesshop.backend.entities.Cart;
import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.User;
import com.clothesshop.backend.entities.UserOrder;
import com.clothesshop.backend.repos.CartRepository;
import com.clothesshop.backend.repos.ItemInOrderRepository;
import com.clothesshop.backend.repos.OrderRepository;
import com.clothesshop.backend.repos.UserRepository;
import com.clothesshop.backend.services.CartService;
import com.clothesshop.backend.services.ItemService;
import com.clothesshop.backend.services.UserService;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CartServiceImpl implements CartService {

  @Autowired
  ItemService itemService;
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  UserRepository userRepository;

  @Autowired
  ItemInOrderRepository itemInOrderRepository;
  @Autowired
  CartRepository cartRepository;
  @Autowired
  UserService userService;

  @Override
  public Cart getCart(User user) {
    return user.getCart();
  }

  @Override
  @Transactional
  public void mergeLocalCart(Collection<ItemInOrder> itemInOrders, User user) {
    Cart finalCart = user.getCart();
    itemInOrders.forEach(itemInOrder -> {
      Set<ItemInOrder> set = finalCart.getItems();
      Optional<ItemInOrder> old = set.stream()
          .filter(e -> e.getItemId().equals(itemInOrder.getItemId())).findFirst();
      ItemInOrder item;
      if (old.isPresent()) {
        item = old.get();
        item.setCount(itemInOrder.getCount() + item.getCount());
      } else {
        item = itemInOrder;
        item.setCart(finalCart);
        finalCart.getItems().add(item);
      }
      itemInOrderRepository.save(item);
    });
    cartRepository.save(finalCart);

  }

  @Override
  @Transactional
  public void delete(String itemId, User user) {
    var op = user.getCart().getItems().stream().filter(e -> itemId.equals(e.getItemId()))
        .findFirst();
    op.ifPresent(itemInOrder -> {
      itemInOrder.setCart(null);
      itemInOrderRepository.deleteById(itemInOrder.getId());
    });
  }


  @Override
  @Transactional
  public void checkout(User user) {
    // Creat an userOrder
    UserOrder userOrder = new UserOrder(user);
    orderRepository.save(userOrder);

    // clear cart's foreign key & set userOrder's foreign key& decrease stock
    user.getCart().getItems().forEach(itemInOrder -> {
      itemInOrder.setCart(null);
      itemInOrder.setUserOrder(userOrder);
      itemService.decreaseStock(itemInOrder.getItemId(), itemInOrder.getCount());
      itemInOrderRepository.save(itemInOrder);
    });

  }
}
