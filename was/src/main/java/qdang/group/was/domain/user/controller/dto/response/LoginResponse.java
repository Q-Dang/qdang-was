package qdang.group.was.domain.user.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import qdang.group.was.domain.user.service.dto.response.TokenCollection;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

	private String accessToken;
	private String refreshToken;

	public static LoginResponse from(TokenCollection jwtToken) {
		return new LoginResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
	}
}
