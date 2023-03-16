package com.kcommerce.service;

import com.kcommerce.dto.CategoryDto;
import com.kcommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto.Response> getCategoryList() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto.Response::of)
                .collect(Collectors.toList());
    }
}
