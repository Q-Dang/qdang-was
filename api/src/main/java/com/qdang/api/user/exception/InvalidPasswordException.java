package com.qdang.api.user.exception;

import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {

	public InvalidPasswordException() {
		super(ErrorType.INVALID_PASSWORD_EXCEPTION);
	}
}
