package com.clothesshop.backend.enums;

import lombok.Getter;


@Getter
public enum ItemStatusEnum implements CodeEnum {
  UP(0, "Available"),
  DOWN(1, "Unavailable");

  private Integer code;
  private String message;

  ItemStatusEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getStatus(Integer code) {

    for (ItemStatusEnum statusEnum : ItemStatusEnum.values()) {
        if (statusEnum.getCode().equals(code)) {

            return statusEnum.getMessage();
        }
    }

    return "";
  }

  public Integer getCode() {
    return code;
  }
}
