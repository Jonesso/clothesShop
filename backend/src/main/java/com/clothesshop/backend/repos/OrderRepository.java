package com.clothesshop.backend.repos;

import com.clothesshop.backend.entities.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<UserOrder, Integer> {

  UserOrder findByOrderId(Long orderId);

  Page<UserOrder> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);

  Page<UserOrder> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail,
      Pageable pageable);

  Page<UserOrder> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

  Page<UserOrder> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(String buyerPhone,
      Pageable pageable);
}
