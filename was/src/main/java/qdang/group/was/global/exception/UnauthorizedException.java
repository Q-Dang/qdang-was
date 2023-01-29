package qdang.group.was.global.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(ErrorType errorType) {
        super(errorType);
    }

    public UnauthorizedException() {
        super(ErrorType.TOKEN_TIME_EXPIRED_EXCEPTION);
    }
}

