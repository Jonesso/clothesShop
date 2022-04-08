package com.clothesshop.backend.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;


@Entity
@Data
@DynamicUpdate
@NoArgsConstructor
public class ItemCategory implements Serializable {

  @Id
  @GeneratedValue
  private Integer categoryId;

  private String categoryName;

  @NaturalId
  private Integer categoryType;

  private Date createTime;

  private Date updateTime;


  public ItemCategory(String categoryName, Integer categoryType) {
    this.categoryName = categoryName;
    this.categoryType = categoryType;
  }
}
