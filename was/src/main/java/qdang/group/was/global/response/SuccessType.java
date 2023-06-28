package qdang.group.was.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK
     */
    LOGIN_SUCCESS(HttpStatus.OK),
    READ_POST_SUCCESS(HttpStatus.OK),
    READ_POST_LIST_SUCCESS(HttpStatus.OK),

    /**
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED),
    CREATE_BOARD_SUCCESS(HttpStatus.CREATED),
    CREATE_EMOTION_SUCCESS(HttpStatus.CREATED),
    ;


    private final HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
