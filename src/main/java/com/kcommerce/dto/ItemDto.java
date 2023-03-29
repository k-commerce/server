package com.kcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ItemDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private int price;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    public static class OrderCheckedItemRequest {
        private Long id;
        private String name;
        private int price;
        private String description;
        private int quantity;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemSearchCondition {
        private Long categoryId;
        private List<Long> itemIdList;
    }
}
