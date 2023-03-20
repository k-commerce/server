package com.kcommerce.controller;

import com.kcommerce.dto.ItemDto;
import com.kcommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items/{categoryId}")
    public ResponseEntity<List<ItemDto.Response>> getItemList(@PathVariable Long categoryId) {
        return ResponseEntity.ok(itemService.getItemList(categoryId));
    }
}
