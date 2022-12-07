package com.mustache.hospital.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERNAME_NOT_FOUNDED(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    NOT_FOUNDED(HttpStatus.NOT_FOUND, "");

    private HttpStatus httpStatus;
    private String message;
}