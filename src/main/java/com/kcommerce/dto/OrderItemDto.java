package com.kcommerce.dto;

import com.kcommerce.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderItemDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private OrderDto.Response order;
        private ItemDto.Response item;
        private OrderStatus status;
        private int quantity;
        private int orderPrice;
    }
}
