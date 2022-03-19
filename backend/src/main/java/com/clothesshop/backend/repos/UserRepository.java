package com.clothesshop.backend.repos;


import com.clothesshop.backend.entities.User;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

  User findByEmail(String email);

  Collection<User> findAllByRole(String role);

}
