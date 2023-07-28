package com.qdang.application.global.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(ErrorType errorType) {
        super(errorType);
    }

    public UnauthorizedException() {
        super(ErrorType.VALIDATION_UNAUTHORIZED_EXCEPTION);
    }
}