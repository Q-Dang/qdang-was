package com.qdang.application.global.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorType errorType) {
        super(errorType);
    }

    public NotFoundException() {
        super(ErrorType.NOT_FOUND_RESOURCE);
    }
}
