package com.qdang.api.user.service.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class LoginInfo {

	private String userId;
	private String password;

	public static LoginInfo of(String userId, String password) {
		return LoginInfo.builder()
			.userId(userId)
			.password(password)
			.build();
	}
}
