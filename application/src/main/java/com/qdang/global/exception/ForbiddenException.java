package com.qdang.global.exception;

public class ForbiddenException extends BusinessException {

	public ForbiddenException() {
		super(ErrorType.FORBIDDEN_EXCEPTION);
	}

	public ForbiddenException(String message) {
		super(ErrorType.FORBIDDEN_EXCEPTION, message);
	}
}
