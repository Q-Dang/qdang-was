package com.qdang.application.noticeboard.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class NotFoundCommentException extends BusinessException {

	public NotFoundCommentException() {
		super(ErrorType.NOT_FOUND_COMMENT_EXCEPTION);
	}
}
