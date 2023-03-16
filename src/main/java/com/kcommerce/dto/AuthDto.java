package com.kcommerce.dto;

import com.kcommerce.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;

public class AuthDto {

    @Getter
    @Builder
    public static class Response {

        private final Long id;
        private final String username;
        private final String name;
        private final String phoneNumber;
    }

    @Getter
    public static class Request {

        private String username;
        private String password;
    }

    @Getter
    public static class MemberAdapter extends org.springframework.security.core.userdetails.User {

        private final Member member;

        public MemberAdapter(String username, String password, Member member) {
            super(username, password, Collections.emptyList());
            this.member = member;
        }
    }
}
