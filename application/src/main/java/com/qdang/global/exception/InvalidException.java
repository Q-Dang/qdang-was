package com.qdang.global.exception;

public class InvalidException extends BusinessException {

	public InvalidException() {
		super(ErrorType.INVALID_INPUT_EXCEPTION);
	}

	public InvalidException(String message) {
		super(ErrorType.INVALID_INPUT_EXCEPTION, message);
	}
}
