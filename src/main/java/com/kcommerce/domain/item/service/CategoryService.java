package com.kcommerce.domain.item.service;

import com.kcommerce.domain.item.domain.Category;
import com.kcommerce.domain.item.dto.CategoryDto;
import com.kcommerce.domain.item.mapper.CategoryMapper;
import com.kcommerce.domain.item.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto.Response> getCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toDtoList(categoryList);
    }
}
