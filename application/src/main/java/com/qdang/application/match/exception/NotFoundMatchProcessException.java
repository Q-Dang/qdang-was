package com.qdang.application.match.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class NotFoundMatchProcessException extends BusinessException {

	public NotFoundMatchProcessException() {
		super(ErrorType.NOT_FOUND_MATCH_PROCESS_EXCEPTION);
	}
}
