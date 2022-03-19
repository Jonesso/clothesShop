package com.clothesshop.backend.services;

import com.clothesshop.backend.entities.Cart;
import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.User;
import java.util.Collection;


public interface CartService {

  Cart getCart(User user);

  void mergeLocalCart(Collection<ItemInOrder> itemInOrders, User user);

  void delete(String itemId, User user);

  void checkout(User user);
}
