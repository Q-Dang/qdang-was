package com.qdang.api.user.adapter.in.response;

import com.qdang.api.user.service.dto.response.TokenCollection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

	private String accessToken;
	private String refreshToken;

	public static LoginResponse from(TokenCollection jwtToken) {
		return new LoginResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
	}
}
