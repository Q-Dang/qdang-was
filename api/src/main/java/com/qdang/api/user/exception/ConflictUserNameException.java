package com.qdang.api.user.exception;

import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.BusinessException;

public class ConflictUserNameException extends BusinessException {

	public ConflictUserNameException() {
		super(ErrorType.ALREADY_EXIST_USER_EXCEPTION);
	}
}
