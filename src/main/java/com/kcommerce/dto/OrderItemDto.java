package com.kcommerce.dto;

import com.kcommerce.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

public class OrderItemDto {

    @Getter
    @Builder
    public static class Response {

        private Long id;
        private OrderDto.Response order;
        private ItemDto.Response item;
        private OrderStatus status;
        private int quantity;
        private int orderPrice;
    }

    @Getter
    public static class Request {

        private Long id;
    }
}
