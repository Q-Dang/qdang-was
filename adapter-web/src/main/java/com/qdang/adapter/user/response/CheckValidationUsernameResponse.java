package com.qdang.adapter.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckValidationUsernameResponse {

	@Schema(description = "닉네임 유효 여부")
	private Boolean isValid;

	public static CheckValidationUsernameResponse from(Boolean isValid) {
		return new CheckValidationUsernameResponse(isValid);
	}
}
