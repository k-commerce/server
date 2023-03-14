package com.kcommerce.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;
}
