package com.qdang.application.user.port.in.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class LoginCommand {

	private String loginId;
	private String password;

	public static LoginCommand of(String loginId, String password) {
		return LoginCommand.builder()
			.loginId(loginId)
			.password(password)
			.build();
	}
}