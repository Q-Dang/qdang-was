package com.qdang.application.user.exception;


import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class ConflictUserException extends BusinessException {

	public ConflictUserException() {
		super(ErrorType.CONFLICT_USER_EXCEPTION);
	}
}
