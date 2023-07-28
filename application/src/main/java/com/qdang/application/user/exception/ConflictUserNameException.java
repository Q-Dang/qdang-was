package com.qdang.application.user.exception;


import com.qdang.application.global.exception.BusinessException;
import com.qdang.application.global.exception.ErrorType;

public class ConflictUserNameException extends BusinessException {

	public ConflictUserNameException() {
		super(ErrorType.ALREADY_EXIST_USER_EXCEPTION);
	}
}
