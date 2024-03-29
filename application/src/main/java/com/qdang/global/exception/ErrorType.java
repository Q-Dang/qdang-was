package com.qdang.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

	/**
	 * 400 BAD REQUEST
	 */
	INVALID_INPUT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
	INVALID_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),
	INVALID_ARGUMENT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 인자가 입력되었습니다."),
	INVALID_USER_ID_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 유저 ID가 입력되었습니다."),

	/**
	 * 401 UNAUTHORIZED
	 */
	UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
	EXPIRED_ACCESS_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 엑세스 토큰입니다."),
	EXPIRED_REFRESH_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 리프레시 토큰입니다."),
	INVALID_ACCESS_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "유효하지 않은 엑세스 토큰입니다."),
	INVALID_REFRESH_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다."),
	UNSUPPORTED_JWT_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "지원하지 않는 JWT 토큰입니다."),

	/**
	 * 403 Forbidden
	 */
	FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN, "리소스에 접근 권한이 없습니다."),
	FORBIDDEN_ADMIN_EXCEPTION(HttpStatus.FORBIDDEN, "관리자에게만 허용된 접근입니다."),

	/**
	 * 404 NOT FOUND
	 */
	NOT_FOUND_RESOURCE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다."),
	NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"),
	NOT_FOUND_MATCH_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 경기입니다"),
	NOT_FOUND_MATCH_PROCESS_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 경기 프로세스입니다"),
	NOT_FOUND_USER_MATCH_PROCESS_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저 경기 프로세스입니다"),
	NOT_FOUND_NOTICE_BOARD_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다"),
	NOT_FOUND_POST_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다"),
	NOT_FOUND_COMMENT_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다"),

	/**
	 * 405 METHOD NOT ALLOWED
	 */
	METHOD_NOT_ALLOWED_EXCEPTION(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드입니다."),

	/**
	 * 409 CONFLICT
	 */
	CONFLICT_RESOURCE_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 데이터입니다."),
	CONFLICT_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저입니다"),

	/**
	 * 500 INTERNAL SERVER ERROR
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다"),
	;

	private final HttpStatus httpStatus;
	private final String message;

	public int getStatusCode() {
		return httpStatus.value();
	}

	public String getMessage() {
		return message;
	}
}
