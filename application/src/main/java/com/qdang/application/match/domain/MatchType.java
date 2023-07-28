package com.qdang.application.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchType {

	THREE_BALL(2, "THREE_BALL"),
	FOUR_BALL(1, "FOUR_BALL"),
	POCKET_BALL(0, "POCKET_BALL");

	private final int code;
	private final String name;

	public static MatchType getMatchType(int code) {
		for (MatchType matchType : MatchType.values()) {
			if (matchType.getCode() == code) {
				return matchType;
			}
		}
		// Todo: Refactoring
		throw new IllegalArgumentException("존재하지 않는 MatchType 입니다.");
	}
}