package com.clothesshop.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Data
@DynamicUpdate
public class ItemInfo implements Serializable {

  @Id
  private String itemId;

  @NotNull
  private String itemName;

  @NotNull
  private BigDecimal itemPrice;

  @NotNull
  @Min(0)
  private Integer itemStock;

  private String itemDescription;

  private String itemIcon;

  /**
   * 0: on-sale 1: off-sale
   */

  @ColumnDefault("0")
  private Integer itemStatus;

  @ColumnDefault("0")
  private Integer categoryType;

  @CreationTimestamp
  private Date createTime;
  @UpdateTimestamp
  private Date updateTime;

  public ItemInfo() {
  }
}
