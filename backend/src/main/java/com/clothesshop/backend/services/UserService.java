package com.clothesshop.backend.services;

import com.clothesshop.backend.entities.User;
import java.util.Collection;


public interface UserService {

  User findOne(String email);

  Collection<User> findByRole(String role);

  User save(User user);

  User update(User user);
}
