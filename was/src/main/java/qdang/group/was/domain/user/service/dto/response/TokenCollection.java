package qdang.group.was.domain.user.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenCollection {

	private final String accessToken;
	private final String refreshToken;
	public static TokenCollection of(String jwtToken, String refreshToken) {
		return new TokenCollection(jwtToken, refreshToken);
	}
}
