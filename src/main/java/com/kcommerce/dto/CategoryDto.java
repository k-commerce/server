package com.kcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class CategoryDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long Id;
        private String name;
        private int depth;
        private List<Response> childList;
    }
}
