package com.clothesshop.backend.api;


import com.clothesshop.backend.entities.Cart;
import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.User;
import com.clothesshop.backend.form.ItemForm;
import com.clothesshop.backend.repos.ItemInOrderRepository;
import com.clothesshop.backend.services.CartService;
import com.clothesshop.backend.services.ItemInOrderService;
import com.clothesshop.backend.services.ItemService;
import com.clothesshop.backend.services.UserService;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartService cartService;
  @Autowired
  UserService userService;
  @Autowired
  ItemService itemService;
  @Autowired
  ItemInOrderService itemInOrderService;
  @Autowired
  ItemInOrderRepository itemInOrderRepository;

  @PostMapping("")
  public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ItemInOrder> itemInOrders,
      Principal principal) {
    User user = userService.findOne(principal.getName());
    try {
      cartService.mergeLocalCart(itemInOrders, user);
    } catch (Exception e) {
      ResponseEntity.badRequest().body("Merge Cart Failed");
    }
    return ResponseEntity.ok(cartService.getCart(user));
  }

  @GetMapping("")
  public Cart getCart(Principal principal) {
    User user = userService.findOne(principal.getName());
    return cartService.getCart(user);
  }


  @PostMapping("/add")
  public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
    var itemInfo = itemService.findOne(form.getItemId());
    try {
      mergeCart(Collections.singleton(new ItemInOrder(itemInfo, form.getQuantity())), principal);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @PutMapping("/{itemId}")
  public ItemInOrder modifyItem(@PathVariable("itemId") String itemId,
      @RequestBody Integer quantity, Principal principal) {
    User user = userService.findOne(principal.getName());
    itemInOrderService.update(itemId, quantity, user);
    return itemInOrderService.findOne(itemId, user);
  }

  @DeleteMapping("/{itemId}")
  public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
    User user = userService.findOne(principal.getName());
    cartService.delete(itemId, user);
  }


  @PostMapping("/checkout")
  public ResponseEntity checkout(Principal principal) {
    User user = userService.findOne(principal.getName()); // Email as username
    cartService.checkout(user);
    return ResponseEntity.ok(null);
  }


}
