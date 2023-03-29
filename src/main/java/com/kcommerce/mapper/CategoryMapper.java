package com.kcommerce.mapper;

import com.kcommerce.domain.Category;
import com.kcommerce.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    default CategoryDto.Response toDto(Category category) {
        return new CategoryDto.Response(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getParent() != null ? category.getParent().getId() : null
        );
    }

    List<CategoryDto.Response> toDtoList(List<Category> categoryList);
}
