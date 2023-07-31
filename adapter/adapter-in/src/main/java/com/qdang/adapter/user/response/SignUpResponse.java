package com.qdang.adapter.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpResponse {

	@Schema(description = "생성된 userId")
	private Long userId;

	public static SignUpResponse from(Long userId) {
		return new SignUpResponse(userId);
	}
}
