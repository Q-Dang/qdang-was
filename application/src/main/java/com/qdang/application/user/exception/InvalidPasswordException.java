package com.qdang.application.user.exception;


import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class InvalidPasswordException extends BusinessException {

	public InvalidPasswordException() {
		super(ErrorType.INVALID_PASSWORD_EXCEPTION);
	}
}
