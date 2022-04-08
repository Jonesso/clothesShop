package com.clothesshop.backend.repos;

import com.clothesshop.backend.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Integer> {
}
