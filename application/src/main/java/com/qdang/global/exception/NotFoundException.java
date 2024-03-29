package com.qdang.global.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException() {
        super(ErrorType.NOT_FOUND_RESOURCE_EXCEPTION);
    }

    public NotFoundException(String message) {
        super(ErrorType.NOT_FOUND_RESOURCE_EXCEPTION, message);
    }
}
