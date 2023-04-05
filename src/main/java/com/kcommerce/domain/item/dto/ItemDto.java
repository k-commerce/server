package com.kcommerce.domain.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ItemDto {

    @Getter
    @Builder
    public static class Response {

        private Long id;
        private String name;
        private int price;
        private String description;
    }

    @Getter
    @Setter
    public static class ItemSearchCondition {

        private Long categoryId;
        private List<Long> itemIdList;
    }
}
