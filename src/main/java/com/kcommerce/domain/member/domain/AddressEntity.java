package com.kcommerce.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "address")
@Getter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    @Embedded
    private Address address;

    @Builder
    private AddressEntity(Member member, String name, Address address) {
        this.member = member;
        this.name = name;
        this.address = address;
    }

    public void update(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
