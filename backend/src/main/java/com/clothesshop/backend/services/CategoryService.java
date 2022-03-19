package com.clothesshop.backend.services;

import com.clothesshop.backend.entities.ItemCategory;
import java.util.List;


public interface CategoryService {

  List<ItemCategory> findAll();

  ItemCategory findByCategoryType(Integer categoryType);

  List<ItemCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

  ItemCategory save(ItemCategory itemCategory);


}
