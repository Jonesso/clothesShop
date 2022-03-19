package com.clothesshop.backend.services.impl;


import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.ItemInfo;
import com.clothesshop.backend.entities.UserOrder;
import com.clothesshop.backend.enums.OrderStatusEnum;
import com.clothesshop.backend.enums.ResultEnum;
import com.clothesshop.backend.exceptions.CustomException;
import com.clothesshop.backend.repos.ItemInOrderRepository;
import com.clothesshop.backend.repos.ItemInfoRepository;
import com.clothesshop.backend.repos.OrderRepository;
import com.clothesshop.backend.repos.UserRepository;
import com.clothesshop.backend.services.ItemService;
import com.clothesshop.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderRepository orderRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  ItemInfoRepository itemInfoRepository;
  @Autowired
  ItemService itemService;
  @Autowired
  ItemInOrderRepository itemInOrderRepository;

  @Override
  public Page<UserOrder> findAll(Pageable pageable) {
    return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
  }

  @Override
  public Page<UserOrder> findByStatus(Integer status, Pageable pageable) {
    return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
  }

  @Override
  public Page<UserOrder> findByBuyerEmail(String email, Pageable pageable) {
    return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
  }

  @Override
  public Page<UserOrder> findByBuyerPhone(String phone, Pageable pageable) {
    return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
  }

  @Override
  public UserOrder findOne(Long orderId) {
    UserOrder userOrder = orderRepository.findByOrderId(orderId);
    if (userOrder == null) {
      throw new CustomException(ResultEnum.ORDER_NOT_FOUND);
    }
    return userOrder;
  }

  @Override
  @Transactional
  public UserOrder finish(Long orderId) {
    UserOrder userOrder = findOne(orderId);
    if (!userOrder.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
      throw new CustomException(ResultEnum.ORDER_STATUS_ERROR);
    }

    userOrder.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    orderRepository.save(userOrder);
    return orderRepository.findByOrderId(orderId);
  }

  @Override
  @Transactional
  public UserOrder cancel(Long orderId) {
    UserOrder userOrder = findOne(orderId);
    if (!userOrder.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
      throw new CustomException(ResultEnum.ORDER_STATUS_ERROR);
    }

    userOrder.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
    orderRepository.save(userOrder);

    // Restore Stock
    Iterable<ItemInOrder> items = userOrder.getItems();
    for (ItemInOrder itemInOrder : items) {
      ItemInfo itemInfo = itemInfoRepository.findByItemId(itemInOrder.getItemId());
      if (itemInfo != null) {
        itemService.increaseStock(itemInOrder.getItemId(), itemInOrder.getCount());
      }
    }
    return orderRepository.findByOrderId(orderId);

  }
}
