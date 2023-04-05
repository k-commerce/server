package com.kcommerce.domain.item.mapper;

import com.kcommerce.domain.item.domain.Category;
import com.kcommerce.domain.item.dto.CategoryDto;
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
