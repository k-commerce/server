package com.kcommerce.domain.item.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private int price;
    private String description;

    @Builder
    public Item(String name, String imageUrl, int price, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
    }
}
