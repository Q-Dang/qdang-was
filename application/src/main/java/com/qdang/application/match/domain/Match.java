package com.qdang.application.match.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// TODO: Domain 모델로 고치기
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

	public static Match of(MatchType matchType, Integer userCount) {
		return Match.builder()
			.matchType(matchType)
			.userCount(userCount)
			.build();
	}
}
