package com.qdang.application.user.port.in.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpCommand {

	private String loginId;
	private String password;

	public static SignUpCommand of(
		String loginId,
		String password) {
		return SignUpCommand.builder()
			.loginId(loginId)
			.password(password)
			.build();
	}
}
