package com.clothesshop.backend.api;


import com.clothesshop.backend.dto.response.CategoryPage;
import com.clothesshop.backend.entities.ItemCategory;
import com.clothesshop.backend.entities.ItemInfo;
import com.clothesshop.backend.services.CategoryService;
import com.clothesshop.backend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class CategoryController {

  @Autowired
  CategoryService categoryService;
  @Autowired
  ItemService itemService;


  // Show items in category
  @GetMapping("/category/{type}")
  public CategoryPage showOne(@PathVariable("type") Integer categoryType,
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "size", defaultValue = "3") Integer size) {

    ItemCategory cat = categoryService.findByCategoryType(categoryType);
    PageRequest request = PageRequest.of(page - 1, size);
    Page<ItemInfo> itemInCategory = itemService.findAllInCategory(categoryType, request);
    var tmp = new CategoryPage("", itemInCategory);
    tmp.setCategory(cat.getCategoryName());
    return tmp;
  }
}
