package com.kcommerce.mapper;

import com.kcommerce.domain.Category;
import com.kcommerce.dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto.Response toDto(Category category) {
        return new CategoryDto.Response(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getChildList()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    public List<CategoryDto.Response> toDtoList(List<Category> categoryList) {
        return categoryList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
