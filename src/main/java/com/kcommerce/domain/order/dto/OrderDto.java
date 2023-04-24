package com.kcommerce.domain.order.dto;

import com.kcommerce.domain.order.domain.Address;
import com.kcommerce.domain.order.domain.PaymentType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class OrderDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private Address address;
    private PaymentType payment;
    private LocalDateTime createdDate;
    private List<OrderItemDto> orderItemList;

    @Getter
    public static class Request {

        @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{1,9}$", message = "이름은 1~9자의 영문, 숫자, 한글만 사용 가능합니다.")
        private String name;

        @Pattern(regexp = "^[0-9]{11}$", message = "휴대전화번호는 11자의 숫자만 사용 가능합니다.")
        private String phoneNumber;

        @Pattern(regexp = "^[0-9]{5}$", message = "우편번호는 5자의 숫자로 입력해 주세요.")
        private String postcode;

        @NotBlank(message = "도로명 주소 / 지번 주소는 1~100자로 입력해 주세요.")
        @Size(max = 100, message = "도로명 주소 / 지번 주소는 1~100자로 입력해 주세요.")
        private String selected;

        @Size(max = 100, message = "상세 주소는 100자 이하로 입력해 주세요.")
        private String detailed;

        private PaymentType payment;

        private Map<Long, Integer> orderCheck;
    }
}
