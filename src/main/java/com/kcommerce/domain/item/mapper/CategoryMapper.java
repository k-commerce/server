package com.kcommerce.domain.item.mapper;

import com.kcommerce.domain.item.domain.Category;
import com.kcommerce.domain.item.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    default CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .depth(category.getDepth())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .build();
    }

    List<CategoryDto> toDtoList(List<Category> categoryList);
}
