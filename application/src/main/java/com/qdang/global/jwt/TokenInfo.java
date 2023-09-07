package com.qdang.global.jwt;

import com.qdang.application.user.domain.User;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInfo {

	private Map<String, Object> payload;

	public static TokenInfo from(User user) {
		Map<String, Object> accessTokenPayload = new HashMap<>();
		accessTokenPayload.put("userId", user.getId());
//		accessTokenPayload.put("role", user.getUserRole().name());
		return new TokenInfo(accessTokenPayload);
	}
}
