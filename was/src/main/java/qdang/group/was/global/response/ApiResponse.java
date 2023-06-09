package qdang.group.was.global.response;

import org.springframework.http.ResponseEntity;
import qdang.group.was.global.exception.ErrorType;

public class ApiResponse {

    public static ResponseEntity success(SuccessType successType){
        return ResponseEntity
            .status(successType.getHttpStatus()).build();
    }

    public static <T> ResponseEntity<?> success(SuccessType successType, T data){
        return ResponseEntity
                .status(successType.getHttpStatus())
                .body(data);
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
