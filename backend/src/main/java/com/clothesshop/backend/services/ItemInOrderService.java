package com.clothesshop.backend.services;

import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.User;


public interface ItemInOrderService {

  void update(String itemId, Integer quantity, User user);

  ItemInOrder findOne(String itemId, User user);
}
