package com.clothesshop.backend.api;


import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.UserOrder;
import com.clothesshop.backend.services.OrderService;
import com.clothesshop.backend.services.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class OrderController {

  @Autowired
  OrderService orderService;
  @Autowired
  UserService userService;

  @GetMapping("/order")
  public Page<UserOrder> orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size,
      Authentication authentication) {
    PageRequest request = PageRequest.of(page - 1, size);
    Page<UserOrder> orderPage;
    if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
      orderPage = orderService.findByBuyerEmail(authentication.getName(), request);
    } else {
      orderPage = orderService.findAll(request);
    }
    return orderPage;
  }


  @PatchMapping("/order/cancel/{id}")
  public ResponseEntity<UserOrder> cancel(@PathVariable("id") Long orderId,
      Authentication authentication) {
    UserOrder userOrder = orderService.findOne(orderId);
    if (!authentication.getName().equals(userOrder.getBuyerEmail())
        && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok(orderService.cancel(orderId));
  }

  @PatchMapping("/order/finish/{id}")
  public ResponseEntity<UserOrder> finish(@PathVariable("id") Long orderId,
      Authentication authentication) {
    if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok(orderService.finish(orderId));
  }

  @GetMapping("/order/{id}")
  public ResponseEntity show(@PathVariable("id") Long orderId, Authentication authentication) {
    boolean isCustomer = authentication.getAuthorities()
        .contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    UserOrder userOrder = orderService.findOne(orderId);
    if (isCustomer && !authentication.getName().equals(userOrder.getBuyerEmail())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    Collection<ItemInOrder> items = userOrder.getItems();
    return ResponseEntity.ok(userOrder);
  }
}
