package com.kcommerce.domain.member.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Address {

    private String postcode;
    private String selected;
    private String detailed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(postcode, address.postcode) && Objects.equals(selected, address.selected) && Objects.equals(detailed, address.detailed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postcode, selected, detailed);
    }
}
