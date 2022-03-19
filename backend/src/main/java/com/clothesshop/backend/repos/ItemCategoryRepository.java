package com.clothesshop.backend.repos;

import com.clothesshop.backend.entities.ItemCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {

  // Some categories
  List<ItemCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

  // All category
  List<ItemCategory> findAllByOrderByCategoryType();

  // One category
  ItemCategory findByCategoryType(Integer categoryType);
}
