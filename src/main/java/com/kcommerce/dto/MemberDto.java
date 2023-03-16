package com.kcommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MemberDto {

    private final Long id;
    private final String username;
    private final String name;
    private final String phoneNumber;

    @Getter
    public static class JoinRequest {

        private String username;

        @Setter
        private String password;

        private String name;
    }
}
