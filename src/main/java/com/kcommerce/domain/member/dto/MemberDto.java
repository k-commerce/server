package com.kcommerce.domain.member.dto;

import com.kcommerce.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.util.Collections;

@Getter
@Builder
public class MemberDto {

    private final Long id;
    private final String username;
    private final String name;
    private final String phoneNumber;

    @Getter
    public static class MemberAdapter extends org.springframework.security.core.userdetails.User {

        private final Member member;

        public MemberAdapter(String username, String password, Member member) {
            super(username, password, Collections.emptyList());
            this.member = member;
        }
    }

    @Getter
    public static class LoginRequest {

        @Pattern(regexp = "^[a-z]{1,9}$", message = "")
        private String username;

        @Pattern(regexp = "^[a-zA-Z0-9]{1,9}$", message = "")
        private String password;
    }

    @Getter
    public static class JoinRequest {

        @Pattern(regexp = "^[a-z]{1,9}$", message = "아이디는 1~9자의 영문 소문자만 사용 가능합니다.")
        private String username;

        @Pattern(regexp = "^[a-zA-Z0-9]{1,9}$", message = "비밀번호는 1~9자의 영문, 숫자만 사용 가능합니다.")
        private String password;

        @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{1,9}$", message = "이름은 1~9자의 영문, 숫자, 한글만 사용 가능합니다.")
        private String name;

        @Pattern(regexp = "^[0-9]{11}$", message = "휴대전화번호는 11자의 숫자만 사용 가능합니다.")
        private String phoneNumber;
    }
}
