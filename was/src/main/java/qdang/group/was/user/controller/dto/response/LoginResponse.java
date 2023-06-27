package qdang.group.was.user.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import qdang.group.was.user.service.dto.response.JwtToken;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

	private String jwtToken;

	public static LoginResponse from(JwtToken jwtToken) {
		return new LoginResponse(jwtToken.getJwtToken());
	}
}
