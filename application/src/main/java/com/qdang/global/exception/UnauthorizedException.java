package com.qdang.global.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException() {
        super(ErrorType.UNAUTHORIZED_EXCEPTION);
    }

    public UnauthorizedException(ErrorType errorType) {
        super(errorType);
    }

    public UnauthorizedException(String message) {
        super(ErrorType.UNAUTHORIZED_EXCEPTION, message);
    }
}