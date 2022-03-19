package com.clothesshop.backend.services;


import com.clothesshop.backend.entities.ItemInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemService {

  ItemInfo findOne(String itemId);

  // All selling items
  Page<ItemInfo> findUpAll(Pageable pageable);

  // All items
  Page<ItemInfo> findAll(Pageable pageable);

  // All items in a category
  Page<ItemInfo> findAllInCategory(Integer categoryType, Pageable pageable);

  // increase stock
  void increaseStock(String itemId, int amount);

  //decrease stock
  void decreaseStock(String itemId, int amount);

  ItemInfo offSale(String itemId);

  ItemInfo onSale(String itemId);

  ItemInfo update(ItemInfo itemInfo);

  ItemInfo save(ItemInfo itemInfo);

  void delete(String itemId);


}
