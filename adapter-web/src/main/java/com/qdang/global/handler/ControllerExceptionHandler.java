package com.qdang.global.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ConflictException;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.ForbiddenException;
import com.qdang.global.exception.NotFoundException;
import com.qdang.global.exception.UnauthorizedException;
import com.qdang.global.response.FailResponse;
import com.qdang.global.response.HttpResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

	/**
	 * 400 Error 잘못된 요청값
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<FailResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		FieldError fieldError = Objects.requireNonNull(e.getFieldError());
		return HttpResponse.error(
				ErrorType.INVALID_INPUT_EXCEPTION,
				String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField())
		);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ResponseEntity<FailResponse> handleBadRequest(BindException e) {
		FieldError fieldError = Objects.requireNonNull(e.getFieldError());
		return HttpResponse.error(
				ErrorType.INVALID_INPUT_EXCEPTION,
				String.format("%s (%s)", fieldError.getDefaultMessage(), fieldError.getField())
		);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<FailResponse> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException exception) {
		return HttpResponse.error(ErrorType.INVALID_INPUT_EXCEPTION,
				Objects.requireNonNull(exception.getMessage()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
			HttpMessageNotReadableException.class,
			InvalidFormatException.class,
			ServletRequestBindingException.class
	})
	public ResponseEntity<FailResponse> handleInvalidFormatException(Exception e) {
		return HttpResponse.error(ErrorType.INVALID_INPUT_EXCEPTION);
	}

	/**
	 * 400 Error Pageable에 허용하지 않는 정렬기준이 입력된 경우
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<FailResponse> handleConstraintViolationException(
			SQLIntegrityConstraintViolationException e) {
		return HttpResponse.error(ErrorType.INVALID_INPUT_EXCEPTION);
	}

	/**
	 * 400 Error 잘못된 인자값
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<FailResponse> illegalArgumentExceptionAdvice(IllegalArgumentException e) {
		return HttpResponse.error(ErrorType.INVALID_ARGUMENT_EXCEPTION);
	}

	/**
	 * 401 UnAuthorized
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<FailResponse> handleUnAuthorizedException(UnauthorizedException e) {
		return HttpResponse.error(e.getErrorType());
	}

	/**
	 * 403 Forbidden
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<FailResponse> handleForbiddenException(ForbiddenException e) {
		return HttpResponse.error(e.getErrorType());
	}

	/**
	 * 404 Error Not Found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<FailResponse> handleNotFoundException(NotFoundException e) {
		return HttpResponse.error(e.getErrorType());
	}

	/**
	 * 405 Error Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<FailResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		return HttpResponse.error(ErrorType.METHOD_NOT_ALLOWED_EXCEPTION);
	}

	/**
	 * 409 Error Conflict
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<FailResponse> handleConflictException(ConflictException e) {
		return HttpResponse.error(e.getErrorType());
	}

	/**
	 * 500 Error Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<FailResponse> handleInternalServerException(Exception e) {
		return HttpResponse.error(ErrorType.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Custom Error
	 */
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<FailResponse> handleBusinessException(BusinessException e) {
		return HttpResponse.error(e.getErrorType(), e.getMessage());
	}
}
