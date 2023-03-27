package com.kcommerce.dto;

import com.kcommerce.domain.Address;
import com.kcommerce.domain.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    @Getter
    @Setter
    public static class Request {
        private PaymentType payment;
        private String name;
        private String phoneNumber;

        @Pattern(regexp = "^[0-9]{5}$", message = "우편번호는 5자의 숫자로 입력해 주세요.")
        private String postcode;

        @NotBlank(message = "도로명 주소 / 지번 주소는 1~100자로 입력해 주세요.")
        @Size(max = 100, message = "도로명 주소 / 지번 주소는 1~100자로 입력해 주세요.")
        private String selected;

        @Size(max = 100, message = "상세 주소는 100자 이하로 입력해 주세요.")
        private String detailed;

        private List<Long> itemIdList;
        private List<Integer> countList;
        private List<Integer> totalPriceList;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private PaymentType payment;
        private LocalDateTime createdDate;
        private String name;
        private String phoneNumber;
        private Address address;
    }
}
