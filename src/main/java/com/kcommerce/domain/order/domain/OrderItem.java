package com.kcommerce.domain.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long itemId;
    private String itemName;

    private int quantity;
    private int orderPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public OrderItem(Order order, Long itemId, String itemName, int quantity, int orderPrice, OrderStatus status) {
        this.order = order;
        this.itemId = itemId;
        this.itemName = itemName;
        this.status = status;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
