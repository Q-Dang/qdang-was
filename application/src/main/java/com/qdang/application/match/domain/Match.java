package com.qdang.application.match.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Match {

	private Long id;
	private MatchType matchType;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean isDeleted;
	private Boolean isValid;
	private LocalDateTime endAt;
	private LocalTime duration;
	private Integer userCount;

	public static Match newMatch(
			MatchType matchType,
			Integer userCount) {
		return Match.builder()
				.matchType(matchType)
				.userCount(userCount)
				.isValid(false)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();
	}

	public void quit(LocalTime duration) {
		this.isValid = true;
		this.updatedAt = LocalDateTime.now();
		this.endAt = LocalDateTime.now();
		this.duration = duration;
	}

	public boolean isQuitMatch() {
		return this.isValid;
	}
}
