package com.clothesshop.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class ItemInOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JsonIgnore
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  @JsonIgnore
  private UserOrder userOrder;


  @NotEmpty
  private String itemId;

  @NotEmpty
  private String itemName;

  @NotNull
  private String itemDescription;

  private String itemIcon;

  @NotNull
  private Integer categoryType;

  @NotNull
  private BigDecimal itemPrice;

  @Min(0)
  private Integer itemStock;

  @Min(1)
  private Integer count;


  public ItemInOrder(ItemInfo itemInfo, Integer quantity) {
    this.itemId = itemInfo.getItemId();
    this.itemName = itemInfo.getItemName();
    this.itemDescription = itemInfo.getItemDescription();
    this.itemIcon = itemInfo.getItemIcon();
    this.categoryType = itemInfo.getCategoryType();
    this.itemPrice = itemInfo.getItemPrice();
    this.itemStock = itemInfo.getItemStock();
    this.count = quantity;
  }

  @Override
  public String toString() {
    return "ItemInOrder{" +
        "id=" + id +
        ", itemId='" + itemId + '\'' +
        ", itemName='" + itemName + '\'' +
        ", itemDescription='" + itemDescription + '\'' +
        ", itemIcon='" + itemIcon + '\'' +
        ", categoryType=" + categoryType +
        ", itemPrice=" + itemPrice +
        ", itemStock=" + itemStock +
        ", count=" + count +
        '}';
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
      if (!super.equals(o)) {
          return false;
      }

    ItemInOrder item = (ItemInOrder) o;

    return Objects.equals(id, item.id) &&
        Objects.equals(itemId, item.itemId) &&
        Objects.equals(itemName, item.itemName) &&
        Objects.equals(itemDescription, item.itemDescription) &&
        Objects.equals(itemIcon, item.itemIcon) &&
        Objects.equals(categoryType, item.categoryType) &&
        Objects.equals(itemPrice, item.itemPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, itemId, itemName, itemDescription, itemIcon,
        categoryType, itemPrice);
  }
}
