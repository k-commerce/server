package com.kcommerce.domain.order.dto;

import com.kcommerce.domain.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

public class OrderItemDto {

    @Getter
    @Builder
    public static class Response {

        private Long id;
        private Long itemId;
        private String itemName;
        private int quantity;
        private int orderPrice;
        private OrderStatus status;
    }

    @Getter
    public static class Request {

        private Long id;
    }
}
