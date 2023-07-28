package com.qdang.application.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRole {
	MEMBER("MEMBER"),
	ADMIN("ADMIN");

	private final String name;

	public static UserRole getUserRole(String name) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.getName().equals(name)) {
				return userRole;
			}
		}
		// Todo: Refactoring
		throw new IllegalArgumentException("존재하지 않는 UserRole 입니다.");
	}
}
