package com.kcommerce.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String postcode;
    private String selected;
    private String detailed;
}
