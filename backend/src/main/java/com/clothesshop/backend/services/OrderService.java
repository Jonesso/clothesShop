package com.clothesshop.backend.services;


import com.clothesshop.backend.entities.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

  Page<UserOrder> findAll(Pageable pageable);

  Page<UserOrder> findByStatus(Integer status, Pageable pageable);

  Page<UserOrder> findByBuyerEmail(String email, Pageable pageable);

  Page<UserOrder> findByBuyerPhone(String phone, Pageable pageable);

  UserOrder findOne(Long orderId);

  UserOrder finish(Long orderId);

  UserOrder cancel(Long orderId);
}
