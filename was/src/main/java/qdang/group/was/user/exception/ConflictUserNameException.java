package qdang.group.was.user.exception;

import qdang.group.was.global.exception.BusinessException;
import qdang.group.was.global.exception.ErrorType;

public class AlreadyExistUserException extends BusinessException {

	public AlreadyExistUserException() {
		super(ErrorType.ALREADY_EXIST_USER_EXCEPTION);
	}
}
