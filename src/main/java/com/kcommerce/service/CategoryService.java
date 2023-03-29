package com.kcommerce.service;

import com.kcommerce.domain.Category;
import com.kcommerce.dto.CategoryDto;
import com.kcommerce.mapper.CategoryMapper;
import com.kcommerce.repository.CategoryRepository;
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
