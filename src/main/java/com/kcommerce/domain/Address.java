package com.kcommerce.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String postcode;
    private String selected;
    private String detailed;
}
