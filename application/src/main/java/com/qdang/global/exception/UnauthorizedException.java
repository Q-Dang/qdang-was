package com.qdang.global.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException() {
        super(ErrorType.UNAUTHORIZED_EXCEPTION);
    }

    public UnauthorizedException(ErrorType errorType) {
        super(ErrorType.UNAUTHORIZED_EXCEPTION, errorType.getMessage());
    }

    public UnauthorizedException(String message) {
        super(ErrorType.UNAUTHORIZED_EXCEPTION, message);
    }
}