package qdang.group.was.global.response;

import org.springframework.http.ResponseEntity;
import qdang.group.was.global.exception.ErrorType;

public class ApiResponse {

    public static ResponseEntity<?> success(SuccessType successType){
        SuccessResponse<Object> response = new SuccessResponse<>(successType.getHttpStatusCode(), successType.getMessage());
        return ResponseEntity
                .status(successType.getHttpStatusCode())
                .body(response);
    }

    public static <T> ResponseEntity<?> success(SuccessType successType, T data){
        SuccessResponse<T> response = new SuccessResponse<>(successType.getHttpStatusCode(), successType.getMessage(), data);
        return ResponseEntity
                .status(successType.getHttpStatusCode())
                .body(response);
    }

    public static ResponseEntity<?> error(ErrorType errorType) {
        FailResponse response = new FailResponse(errorType.getStatusCode(), errorType.getMessage());
        return ResponseEntity
                .status(errorType.getStatusCode())
                .body(response);
    }

    public static ResponseEntity<?> error(ErrorType errorType, String message) {
        FailResponse response = new FailResponse(errorType.getStatusCode(), message);
        return ResponseEntity
                .status(errorType.getStatusCode())
                .body(response);
    }
}
