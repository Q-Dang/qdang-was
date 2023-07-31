package com.qdang.adapter.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    READ_RESOURCE_SUCCESS(HttpStatus.OK, "조회에 성공했습니다."),
    READ_RESOURCE_LIST_SUCCESS(HttpStatus.OK, "리스트 조회에 성공했습니다."),

    /**
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_RESOURCE_SUCCESS(HttpStatus.CREATED, "생성에 성공했습니다."),

    /**
     * 204 NO CONTENT
     */
    UPDATE_RESOURCE_SUCCESS(HttpStatus.NO_CONTENT, "갱신에 성공했습니다."),
    DELETE_RESOURCE_SUCCESS(HttpStatus.NO_CONTENT, "삭제에 성공했습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
