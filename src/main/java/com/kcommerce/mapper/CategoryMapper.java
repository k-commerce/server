package com.kcommerce.mapper;

import com.kcommerce.domain.Category;
import com.kcommerce.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    default CategoryDto.Response toDto(Category category) {
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

    List<CategoryDto.Response> toDtoList(List<Category> categoryList);
}
