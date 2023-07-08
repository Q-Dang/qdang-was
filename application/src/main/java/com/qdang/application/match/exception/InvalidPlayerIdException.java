package com.qdang.application.match.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class InvalidPlayerIdException extends BusinessException {

	public InvalidPlayerIdException() {
		super(ErrorType.INVALID_USER_ID_EXCEPTION);
	}
}
