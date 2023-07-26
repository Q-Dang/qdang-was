package qdang.group.was.domain.user.exception;

import qdang.group.was.global.exception.BusinessException;
import qdang.group.was.global.exception.ErrorType;

public class NotFoundUserException extends BusinessException {

	public NotFoundUserException() {
		super(ErrorType.NOT_FOUND_USER_EXCEPTION);
	}
}
