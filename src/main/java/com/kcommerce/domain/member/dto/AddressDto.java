package com.kcommerce.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
public class AddressDto {

    private Long id;
    private String name;
    private String postcode;
    private String selected;
    private String detailed;

    @Getter
    public static class Request {

        @Null
        private Long id;

        @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{1,9}$", message = "배송지 이름은 1~9자의 영문, 숫자, 한글만 사용 가능합니다.")
        private String name;

        @Pattern(regexp = "^[0-9]{5}$", message = "우편번호는 5자의 숫자로 입력해 주세요.")
        private String postcode;

        @NotBlank(message = "도로명 주소 / 지번 주소는 1~100자로 입력해 주세요.")
        @Size(max = 100, message = "도로명 주소 / 지번 주소는 1~100자로 입력해 주세요.")
        private String selected;

        @Size(max = 100, message = "상세 주소는 100자 이하로 입력해 주세요.")
        private String detailed;
    }
}
