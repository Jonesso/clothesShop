package com.clothesshop.backend.repos;

import com.clothesshop.backend.entities.ItemInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemInfoRepository extends JpaRepository<ItemInfo, String> {

  ItemInfo findByItemId(String id);

  // on-sale product
  Page<ItemInfo> findAllByItemStatusOrderByItemIdAsc(Integer productStatus, Pageable pageable);

  // product in one category
  Page<ItemInfo> findAllByCategoryTypeOrderByItemIdAsc(Integer categoryType, Pageable pageable);

  Page<ItemInfo> findAllByOrderByItemId(Pageable pageable);

}
