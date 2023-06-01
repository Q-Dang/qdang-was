package qdang.group.was.global.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(ErrorType errorType, String message) {
        super(errorType, message);
    }

    public UnauthorizedException() {
        super(ErrorType.TOKEN_TIME_EXPIRED_EXCEPTION);
    }
}

