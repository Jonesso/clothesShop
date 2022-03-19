package com.clothesshop.backend.api;

import com.clothesshop.backend.entities.ItemInfo;
import com.clothesshop.backend.services.CategoryService;
import com.clothesshop.backend.services.ItemService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class ItemController {

  @Autowired
  CategoryService categoryService;
  @Autowired
  ItemService itemService;

  /**
   * Show All Categories
   */

  @GetMapping("/item")
  public Page<ItemInfo> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "size", defaultValue = "3") Integer size) {
    PageRequest request = PageRequest.of(page - 1, size);
    return itemService.findAll(request);
  }

  @GetMapping("/item/{itemId}")
  public ItemInfo showOne(@PathVariable("itemId") String itemId) {

    return itemService.findOne(itemId);
  }

  @PostMapping("/seller/item/new")
  public ResponseEntity create(@Valid @RequestBody ItemInfo item,
      BindingResult bindingResult) {
    ItemInfo itemIdExists = itemService.findOne(item.getItemId());
    if (itemIdExists != null) {
      bindingResult
          .rejectValue("itemId", "error.item",
              "There is already a item with the code provided");
    }
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult);
    }
    return ResponseEntity.ok(itemService.save(item));
  }

  @PutMapping("/seller/item/{id}/edit")
  public ResponseEntity edit(@PathVariable("id") String itemId,
      @Valid @RequestBody ItemInfo item,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult);
    }
    if (!itemId.equals(item.getItemId())) {
      return ResponseEntity.badRequest().body("Id Not Matched");
    }

    return ResponseEntity.ok(itemService.update(item));
  }

  @DeleteMapping("/seller/product/{id}/delete")
  public ResponseEntity delete(@PathVariable("id") String itemId) {
    itemService.delete(itemId);
    return ResponseEntity.ok().build();
  }

}
