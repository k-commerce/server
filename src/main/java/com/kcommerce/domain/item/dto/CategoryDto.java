package com.kcommerce.domain.item.dto;

import lombok.Builder;
import lombok.Getter;

public class CategoryDto {

    @Getter
    @Builder
    public static class Response {

        private Long id;
        private String name;
        private int depth;
        private Long parentId;
    }
}
