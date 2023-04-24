package com.kcommerce.domain.order.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.status = status;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
