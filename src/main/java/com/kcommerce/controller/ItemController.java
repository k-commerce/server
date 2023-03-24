package com.kcommerce.controller;

import com.kcommerce.dto.ItemDto;
import com.kcommerce.dto.ItemSearchCondition;
import com.kcommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<List<ItemDto.Response>> getItemList(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) List<Long> itemIds) {
        ItemSearchCondition itemSearchCondition = new ItemSearchCondition(categoryId, itemIds);
        return ResponseEntity.ok(itemService.getItemList(itemSearchCondition));
    }

    @GetMapping("/api/items/{itemId}")
    public ResponseEntity<ItemDto.Response> getItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItem(itemId));
    }
}
