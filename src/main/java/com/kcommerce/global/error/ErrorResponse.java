package com.kcommerce.global.error;

import com.kcommerce.global.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;
    private final List<FieldError> errors;

    private ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errors = errors;
    }

    static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(errorCode, FieldError.of(bindingResult));
    }

    @Getter
    @Builder
    private static class FieldError {

        final String field;
        final String value;
        final String reason;

        static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> FieldError.builder()
                            .field(error.getField())
                            .value(error.getRejectedValue() == null ? "" : error.getRejectedValue().toString())
                            .reason(error.getDefaultMessage())
                            .build())
                    .collect(Collectors.toList());
        }
    }
}
