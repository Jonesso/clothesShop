package com.clothesshop.backend.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginForm {

  @NotBlank
  private String username;
  @NotBlank
  private String password;
}
