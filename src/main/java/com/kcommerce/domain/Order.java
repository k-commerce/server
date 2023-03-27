package com.kcommerce.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    private String name;
    private String phoneNumber;

    @Builder
    public Order(Member member, Address address, PaymentType payment, String name, String phoneNumber) {
        this.member = member;
        this.address = address;
        this.payment = payment;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
