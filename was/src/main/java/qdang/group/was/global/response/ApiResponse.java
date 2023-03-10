package qdang.group.was.global.response;

import org.springframework.http.ResponseEntity;
import qdang.group.was.global.exception.ErrorType;

public class ApiResponse {

    public static ResponseEntity<?> success(SuccessType successType){
        JsonResponse<Object> response = new JsonResponse<>(successType.getHttpStatusCode(), successType.getMessage());
        return ResponseEntity
                .status(successType.getHttpStatusCode())
                .body(response);
    }

    public static <T> ResponseEntity<?> success(SuccessType successType, T data){
        JsonResponse<T> response = new JsonResponse<>(successType.getHttpStatusCode(), successType.getMessage(), data);
        return ResponseEntity
                .status(successType.getHttpStatusCode())
                .body(response);
    }

    public static ResponseEntity<?> error(ErrorType errorType) {
        JsonResponse<Object> response = new JsonResponse<>(errorType.getHttpStatusCode(), errorType.getMessage());
        return ResponseEntity
                .status(errorType.getHttpStatusCode())
                .body(response);
    }

    public static ResponseEntity<?> error(ErrorType errorType, String message) {
        JsonResponse<Object> response = new JsonResponse<>(errorType.getHttpStatusCode(), message);
        return ResponseEntity
                .status(errorType.getHttpStatusCode())
                .body(response);
    }
}
