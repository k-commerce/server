package com.kcommerce.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int quantity;
    private int orderPrice;

    @Builder
    public OrderItem(Order order, Item item, OrderStatus status, int quantity, int orderPrice) {
        this.order = order;
        this.item = item;
        this.status = status;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
