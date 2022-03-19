package com.clothesshop.backend.enums;

import lombok.Getter;


@Getter
public enum ResultEnum {

  PARAM_ERROR(1, "Parameter Error!"),
  ITEM_NOT_EXIST(10, "Item does not exit!"),
  ITEM_NOT_ENOUGH(11, "Not enough items in stock!"),
  ITEM_STATUS_ERROR(12, "Status is incorrect!"),
  ITEM_OFF_SALE(13, "Item is off sale!"),
  ITEM_NOT_IN_CART(14, "Item is not in the cart!"),
  CART_CHECKOUT_SUCCESS(20, "Checkout successfully! "),

  CATEGORY_NOT_FOUND(30, "Category does not exit!"),

  ORDER_NOT_FOUND(60, "Order does not exit!"),
  ORDER_STATUS_ERROR(61, "Status is not correct"),


  VALID_ERROR(50, "Wrong information"),
  USER_NOT_FOUND(40, "User not found!");

  private Integer code;

  private String message;

  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
