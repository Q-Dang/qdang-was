package qdang.group.was.domain.user.exception;

import qdang.group.was.global.exception.BusinessException;
import qdang.group.was.global.exception.ErrorType;

public class InvalidPasswordException extends BusinessException {

	public InvalidPasswordException() {
		super(ErrorType.INVALID_PASSWORD_EXCEPTION);
	}
}
