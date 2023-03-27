package com.kcommerce.dto;

import com.kcommerce.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderItemDto {

    @Getter
    public static class Request {
        private Long orderItemId;
    }

    @Getter
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
