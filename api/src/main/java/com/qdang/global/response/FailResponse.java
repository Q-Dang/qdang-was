package com.qdang.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FailResponse {

	@Schema(description = "HTTP 상태코드")
	private int code;

	@Schema(description = "에러 메시지")
	private String message;
}
