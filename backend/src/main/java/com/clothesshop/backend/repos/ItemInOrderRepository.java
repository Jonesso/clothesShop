package com.clothesshop.backend.repos;

import com.clothesshop.backend.entities.ItemInOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemInOrderRepository extends JpaRepository<ItemInOrder, Long> {
}
