package qdang.group.was.global.jwt;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import qdang.group.was.user.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInfo {

	private Map<String, Object> payload;

	/*
	public static TokenInfo newTokenInfo(Long userId, UserRole role) {
		Map<String, Object> accessTokenPayload = new HashMap<>();
		accessTokenPayload.put("userId", userId);
		accessTokenPayload.put("role", role);
		return new TokenInfo(accessTokenPayload);
	}
	*/

	public static TokenInfo from(User user) {
		Map<String, Object> accessTokenPayload = new HashMap<>();
		accessTokenPayload.put("userId", user.getId());
		accessTokenPayload.put("role", user.getUserRole());
		return new TokenInfo(accessTokenPayload);
	}
}
