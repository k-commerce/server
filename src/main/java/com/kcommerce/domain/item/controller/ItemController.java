package com.kcommerce.domain.item.controller;

import com.kcommerce.domain.item.dto.ItemDto;
import com.kcommerce.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<List<ItemDto>> getItemList(@ModelAttribute ItemDto.ItemSearchCondition itemSearchCondition) {
        return ResponseEntity.ok(itemService.getItemList(itemSearchCondition));
    }

    @GetMapping("/api/items/{itemId}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItem(itemId));
    }
}
