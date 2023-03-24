package com.kcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchCondition {
    private Long categoryId;
    private List<Long> itemIdList;
}
