package com.clothesshop.backend.services.impl;


import com.clothesshop.backend.entities.ItemInfo;
import com.clothesshop.backend.enums.ItemStatusEnum;
import com.clothesshop.backend.enums.ResultEnum;
import com.clothesshop.backend.exceptions.CustomException;
import com.clothesshop.backend.repos.ItemInfoRepository;
import com.clothesshop.backend.services.CategoryService;
import com.clothesshop.backend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  ItemInfoRepository itemInfoRepository;

  @Autowired
  CategoryService categoryService;

  @Override
  public ItemInfo findOne(String itemId) {

    return itemInfoRepository.findByItemId(itemId);
  }

  @Override
  public Page<ItemInfo> findUpAll(Pageable pageable) {

    return itemInfoRepository.findAllByItemStatusOrderByItemIdAsc(ItemStatusEnum.UP.getCode(),
        pageable);
  }

  @Override
  public Page<ItemInfo> findAll(Pageable pageable) {

    return itemInfoRepository.findAllByOrderByItemId(pageable);
  }

  @Override
  public Page<ItemInfo> findAllInCategory(Integer categoryType, Pageable pageable) {

    return itemInfoRepository.findAllByCategoryTypeOrderByItemIdAsc(categoryType, pageable);
  }

  @Override
  @Transactional
  public void increaseStock(String itemId, int amount) {
    ItemInfo itemInfo = findOne(itemId);

    if (itemInfo == null) {
      throw new CustomException(ResultEnum.ITEM_NOT_EXIST);
    }

    int update = itemInfo.getItemStock() + amount;

    itemInfo.setItemStock(update);
    itemInfoRepository.save(itemInfo);
  }

  @Override
  @Transactional
  public void decreaseStock(String itemId, int amount) {
    ItemInfo itemInfo = findOne(itemId);

    if (itemInfo == null) {
      throw new CustomException(ResultEnum.ITEM_NOT_EXIST);
    }

    int update = itemInfo.getItemStock() - amount;

    if (update <= 0) {
      throw new CustomException(ResultEnum.ITEM_NOT_ENOUGH);
    }

    itemInfo.setItemStock(update);
    itemInfoRepository.save(itemInfo);
  }

  @Override
  @Transactional
  public ItemInfo offSale(String itemId) {
    ItemInfo itemInfo = findOne(itemId);

    if (itemInfo == null) {
      throw new CustomException(ResultEnum.ITEM_NOT_EXIST);
    }

    if (itemInfo.getItemStatus().equals(ItemStatusEnum.DOWN.getCode())) {
      throw new CustomException(ResultEnum.ITEM_STATUS_ERROR);
    }

    itemInfo.setItemStatus(ItemStatusEnum.DOWN.getCode());

    return itemInfoRepository.save(itemInfo);
  }

  @Override
  @Transactional
  public ItemInfo onSale(String itemId) {
    ItemInfo itemInfo = findOne(itemId);

    if (itemInfo == null) {
      throw new CustomException(ResultEnum.ITEM_NOT_EXIST);
    }

    if (itemInfo.getItemStatus().equals(ItemStatusEnum.UP.getCode())) {
      throw new CustomException(ResultEnum.ITEM_STATUS_ERROR);
    }

    itemInfo.setItemStatus(ItemStatusEnum.UP.getCode());

    return itemInfoRepository.save(itemInfo);
  }

  @Override
  public ItemInfo update(ItemInfo itemInfo) {
    // if null throw exception
    categoryService.findByCategoryType(itemInfo.getCategoryType());

    if (itemInfo.getItemStatus() > 1) {
      throw new CustomException(ResultEnum.ITEM_STATUS_ERROR);
    }

    return itemInfoRepository.save(itemInfo);
  }

  @Override
  public ItemInfo save(ItemInfo itemInfo) {
    return update(itemInfo);
  }

  @Override
  public void delete(String itemId) {
    ItemInfo itemInfo = findOne(itemId);

    if (itemInfo == null) {
      throw new CustomException(ResultEnum.ITEM_NOT_EXIST);
    }

    itemInfoRepository.delete(itemInfo);
  }
}
