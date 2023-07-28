package com.qdang.api.user.application.port.in.command;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateUserProfileCommand {

	private Long userId;
	private String username;
	private LocalDate birthday;
	private String gender;
	private int proficiency;

	public static UpdateUserProfileCommand of(
		Long userId,
		String username,
		LocalDate birthday,
		String gender,
		int proficiency) {
		return UpdateUserProfileCommand.builder()
			.userId(userId)
			.username(username)
			.birthday(birthday)
			.gender(gender)
			.proficiency(proficiency)
			.build();
	}
}
