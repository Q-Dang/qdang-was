package com.qdang.adapter.user.response;

import com.qdang.application.user.domain.TokenCollection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

	@Schema(description = "JWT access token")
	private String accessToken;

	@Schema(description = "JWT refresh token")
	private String refreshToken;

	public static LoginResponse from(TokenCollection jwtToken) {
		return new LoginResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
	}
}
