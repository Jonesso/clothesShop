package com.clothesshop.backend.services.impl;

import com.clothesshop.backend.entities.ItemInOrder;
import com.clothesshop.backend.entities.User;
import com.clothesshop.backend.repos.ItemInOrderRepository;
import com.clothesshop.backend.services.ItemInOrderService;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemInOrderServiceImpl implements ItemInOrderService {

  @Autowired
  ItemInOrderRepository itemInOrderRepository;

  @Override
  @Transactional
  public void update(String itemId, Integer quantity, User user) {
    var op = user.getCart().getItems().stream().filter(e -> itemId.equals(e.getItemId()))
        .findFirst();
    op.ifPresent(itemInOrder -> {
      itemInOrder.setCount(quantity);
      itemInOrderRepository.save(itemInOrder);
    });

  }

  @Override
  public ItemInOrder findOne(String itemId, User user) {
    var op = user.getCart().getItems().stream().filter(e -> itemId.equals(e.getItemId()))
        .findFirst();
    AtomicReference<ItemInOrder> res = new AtomicReference<>();
    op.ifPresent(res::set);
    return res.get();
  }
}
