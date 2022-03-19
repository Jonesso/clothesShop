package com.clothesshop.backend.exceptions;

import com.clothesshop.backend.enums.ResultEnum;


public class CustomException extends RuntimeException {

  private Integer code;

  public CustomException(ResultEnum resultEnum) {
    super(resultEnum.getMessage());

    this.code = resultEnum.getCode();
  }

  public CustomException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
