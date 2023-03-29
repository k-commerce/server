package com.kcommerce.mapper;

import com.kcommerce.domain.Category;
import com.kcommerce.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    default CategoryDto.Response toDto(Category category) {
        return CategoryDto.Response.builder()
                .id(category.getId())
                .name(category.getName())
                .depth(category.getDepth())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .build();
    }

    List<CategoryDto.Response> toDtoList(List<Category> categoryList);
}
