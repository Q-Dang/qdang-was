package com.qdang.adapter.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserValidationResponse {

	@Schema(description = "유효 여부")
	private Boolean isValid;

	public static UserValidationResponse from(Boolean isValid) {
		return new UserValidationResponse(isValid);
	}
}
