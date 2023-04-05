package com.kcommerce.domain.item.controller;

import com.kcommerce.domain.item.dto.CategoryDto;
import com.kcommerce.domain.item.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryDto.Response>> getCategoryList() {
        return ResponseEntity.ok(categoryService.getCategoryList());
    }
}
