package com.clothesshop.backend.dto.response;

import com.clothesshop.backend.entities.ItemInfo;
import org.springframework.data.domain.Page;


public class CategoryPage {

  private String category;
  private Page<ItemInfo> page;

  public CategoryPage(String category, Page<ItemInfo> page) {
    this.category = category;
    this.page = page;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Page<ItemInfo> getPage() {
    return page;
  }

  public void setPage(Page<ItemInfo> page) {
    this.page = page;
  }
}
