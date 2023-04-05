package com.kcommerce.domain.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String name;
    private String phoneNumber;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    @Builder
    public Order(Long memberId, String name, String phoneNumber, Address address, PaymentType payment) {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.payment = payment;
    }
}
