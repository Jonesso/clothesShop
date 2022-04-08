package com.clothesshop.backend.services.impl;


import com.clothesshop.backend.entities.ItemCategory;
import com.clothesshop.backend.enums.ResultEnum;
import com.clothesshop.backend.exceptions.CustomException;
import com.clothesshop.backend.repos.ItemCategoryRepository;
import com.clothesshop.backend.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  ItemCategoryRepository itemCategoryRepository;


  @Override
  public List<ItemCategory> findAll() {
    return itemCategoryRepository.findAllByOrderByCategoryType();
  }

  @Override
  public ItemCategory findByCategoryType(Integer categoryType) {
    ItemCategory res = itemCategoryRepository.findByCategoryType(categoryType);

      if (res == null) {
          throw new CustomException(ResultEnum.CATEGORY_NOT_FOUND);
      }

    return res;
  }

  @Override
  public List<ItemCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

    return itemCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
  }

  @Override
  @Transactional
  public ItemCategory save(ItemCategory itemCategory) {

    return itemCategoryRepository.save(itemCategory);
  }

}
