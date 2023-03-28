package com.kcommerce.controller;

import com.kcommerce.dto.ItemDto;
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
    public ResponseEntity<List<ItemDto.Response>> getItemList(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) List<Long> itemIdList) {
        ItemDto.ItemSearchCondition itemSearchCondition = new ItemDto.ItemSearchCondition(categoryId, itemIdList);
        return ResponseEntity.ok(itemService.getItemList(itemSearchCondition));
    }

    @GetMapping("/api/items/{itemId}")
    public ResponseEntity<ItemDto.Response> getItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItem(itemId));
    }
}
