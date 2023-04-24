package com.kcommerce.domain.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ItemDto {

    private Long id;
    private String name;
    private int price;
    private String description;

    @Getter
    @Setter
    public static class ItemSearchCondition {

        private Long cursorId;
        private String name;
        private Long categoryId;
        private List<Long> itemIdList;
    }
}
