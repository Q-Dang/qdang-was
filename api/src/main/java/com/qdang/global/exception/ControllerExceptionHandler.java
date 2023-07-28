package com.qdang.global.exception;

import com.qdang.application.global.exception.BusinessException;
import com.qdang.application.global.exception.ErrorType;
import com.qdang.global.response.HttpResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

	/**
	 * 400 Error
	 * 잘못된 요청값
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		FieldError fieldError = Objects.requireNonNull(e.getFieldError());
		return HttpResponse.error(ErrorType.VALIDATION_INPUT_EXCEPTION,
			String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
	}

	/**
	 * 400 Error
	 * 잘못된 타입 에러
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> handleBadRequest(BindException e) {
		FieldError fieldError = Objects.requireNonNull(e.getFieldError());
		return HttpResponse.error(ErrorType.VALIDATION_WRONG_TYPE_EXCEPTION,
			String.format("%s (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
	}

	/**
	 * 400 Error
	 * 잘못된 요청 에러
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleDateTimeFormatException1(HttpMessageNotReadableException e) {
		return HttpResponse.error(ErrorType.VALIDATION_INPUT_EXCEPTION);
	}

	/**
	 * 400 Error
	 * Pageable에 허용하지 않는 정렬기준이 입력된 경우
	 */
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(SQLIntegrityConstraintViolationException e) {
		return HttpResponse.error(ErrorType.VALIDATION_INPUT_EXCEPTION);
	}

	/**
	 * 400 Error
	 * 잘못된 HTTP Method
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleDateTimeFormatException2(HttpRequestMethodNotSupportedException e) {
		return HttpResponse.error(ErrorType.VALIDATION_WRONG_HTTP_METHOD_EXCEPTION);
	}

	// 나중에 수정하기
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentExceptionAdvice(IllegalArgumentException e) {
		return HttpResponse.error(ErrorType.INVALID_ARGUMENT_EXCEPTION);
	}
	/**
	 * 500 Error
	 * Internal Server Error
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handleInternalServerException(Exception e) {
		System.out.println("e = " + e);
		System.out.println("e.getClass() = " + e.getClass());
		return HttpResponse.error(ErrorType.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Custom Error
	 */
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<?> handleBusinessException(BusinessException e) {
		return HttpResponse.error(e.getErrorType());
	}
}
