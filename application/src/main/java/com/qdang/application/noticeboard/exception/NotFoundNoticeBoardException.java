package com.qdang.application.noticeboard.exception;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;

public class NotFoundNoticeBoardException extends BusinessException {

	public NotFoundNoticeBoardException() {
		super(ErrorType.NOT_FOUND_NOTICE_BOARD_EXCEPTION);
	}
}
