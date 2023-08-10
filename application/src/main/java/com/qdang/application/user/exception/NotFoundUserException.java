package com.qdang.application.user.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class NotFoundUserException extends BusinessException {

	public NotFoundUserException() {
		super(ErrorType.NOT_FOUND_USER_EXCEPTION);
	}
}
