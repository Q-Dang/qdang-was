package com.qdang.api.user.exception;

import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.BusinessException;

public class NotFoundUserException extends BusinessException {

	public NotFoundUserException() {
		super(ErrorType.NOT_FOUND_USER_EXCEPTION);
	}
}
