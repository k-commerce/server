package com.kcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ItemDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String name;
        private int price;
        private String description;
    }
}
