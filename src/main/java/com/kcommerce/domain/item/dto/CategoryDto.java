package com.kcommerce.domain.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDto {

    private Long id;
    private String name;
    private int depth;
    private Long parentId;
}
