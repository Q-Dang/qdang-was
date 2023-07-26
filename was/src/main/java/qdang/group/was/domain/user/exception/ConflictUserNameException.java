package qdang.group.was.domain.user.exception;

import qdang.group.was.global.exception.BusinessException;
import qdang.group.was.global.exception.ErrorType;

public class ConflictUserNameException extends BusinessException {

	public ConflictUserNameException() {
		super(ErrorType.ALREADY_EXIST_USER_EXCEPTION);
	}
}
