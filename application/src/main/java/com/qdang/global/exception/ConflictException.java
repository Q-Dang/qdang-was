package com.qdang.global.exception;

public class ConflictException extends BusinessException {

	public ConflictException() {
		super(ErrorType.CONFLICT_RESOURCE_EXCEPTION);
	}

	public ConflictException(String message) {
		super(ErrorType.CONFLICT_RESOURCE_EXCEPTION, message);
	}
}
