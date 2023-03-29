package com.kcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CategoryDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long Id;
        private String name;
        private int depth;
        private Long parentId;
    }
}
