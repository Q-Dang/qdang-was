package com.qdang.adapter.auth.response;

import com.qdang.application.user.domain.TokenCollection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "토큰 응답")
public class TokenResponse {

	@Schema(description = "JWT access token", example = "access_token")
	private String accessToken;

	@Schema(description = "JWT refresh token", example = "refresh_token")
	private String refreshToken;

	public static TokenResponse from(TokenCollection tokenCollection) {
		return new TokenResponse(tokenCollection.getAccessToken(), tokenCollection.getRefreshToken());
	}
}
