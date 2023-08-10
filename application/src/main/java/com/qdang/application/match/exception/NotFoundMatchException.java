package com.qdang.application.match.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class NotFoundMatchException extends BusinessException {

	public NotFoundMatchException() {
		super(ErrorType.NOT_FOUND_MATCH_EXCEPTION);
	}
}
