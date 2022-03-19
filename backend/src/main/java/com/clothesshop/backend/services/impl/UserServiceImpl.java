package com.clothesshop.backend.services.impl;


import com.clothesshop.backend.entities.Cart;
import com.clothesshop.backend.entities.User;
import com.clothesshop.backend.enums.ResultEnum;
import com.clothesshop.backend.exceptions.CustomException;
import com.clothesshop.backend.repos.CartRepository;
import com.clothesshop.backend.repos.UserRepository;
import com.clothesshop.backend.services.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  UserRepository userRepository;
  @Autowired
  CartRepository cartRepository;

  @Override
  public User findOne(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Collection<User> findByRole(String role) {
    return userRepository.findAllByRole(role);
  }

  @Override
  @Transactional
  public User save(User user) {
    //register
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    try {
      User savedUser = userRepository.save(user);

      // initial Cart
      Cart savedCart = cartRepository.save(new Cart(savedUser));
      savedUser.setCart(savedCart);
      return userRepository.save(savedUser);

    } catch (Exception e) {
      throw new CustomException(ResultEnum.VALID_ERROR);
    }

  }

  @Override
  @Transactional
  public User update(User user) {
    User oldUser = userRepository.findByEmail(user.getEmail());
    oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
    oldUser.setName(user.getName());
    oldUser.setPhone(user.getPhone());
    oldUser.setAddress(user.getAddress());
    return userRepository.save(oldUser);
  }

}
