package com.qdang.application.noticeboard.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class NotFoundPostException extends BusinessException {

	public NotFoundPostException() {
		super(ErrorType.NOT_FOUND_POST_EXCEPTION);
	}
}
