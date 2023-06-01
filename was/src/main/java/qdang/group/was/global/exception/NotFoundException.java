package qdang.group.was.global.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorType errorType, String message) {
        super(errorType, message);
    }

    public NotFoundException() {
        super(ErrorType.NOT_FOUND_RESOURCE);
    }
}
