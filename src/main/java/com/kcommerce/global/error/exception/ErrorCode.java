package com.kcommerce.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    METHOD_ARGUMENT_NOT_VALID(400, "METHOD_ARGUMENT_NOT_VALID", "유효성 검증에 실패하였습니다."),
    BAD_CREDENTIALS(400, "BAD_CREDENTIALS", "로그인에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버에 문제가 발생하였습니다."),

    // Member
    USERNAME_DUPLICATE(400, "USERNAME_DUPLICATE", "이미 존재하는 아이디입니다."),

    // Address
    ADDRESS_NOT_FOUND(404, "ADDRESS_NOT_FOUND", "배송지를 찾을 수 없습니다."),

    // Order
    ALREADY_CANCEL(400, "ALREADY_CANCEL", "이미 취소된 주문입니다."),
    CHECK_MEMBER(400, "CHECK_MEMBER", "로그인된 회원과 주문자가 일치하지 않습니다."),
    ORDER_ITEM_NOT_FOUND(404, "ORDER_ITEM_NOT_FOUND", "주문한 상품을 찾을 수 없습니다."),
    ORDER_NOT_FOUND(404, "ORDER_NOT_FOUND", "주문을 찾을 수 없습니다."),

    // Item
    ITEM_NOT_FOUND(404, "ITEM_NOT_FOUND", "상품을 찾을 수 없습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
