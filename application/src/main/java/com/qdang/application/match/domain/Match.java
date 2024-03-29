package com.qdang.application.match.domain;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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
	private List<UserMatch> userMatches;

	public static Match init(Long id) {
		return Match.builder()
				.id(id)
				.build();
	}

	public static Match newMatch(
			MatchType matchType,
			Integer userCount) {
		return Match.builder()
				.matchType(matchType)
				.userCount(userCount)
				.isValid(false)
				.build();
	}

	public void quit(LocalTime duration) {
		if (this.isValid) {
			throw new BusinessException(ErrorType.INVALID_INPUT_EXCEPTION, "이미 종료된 게임입니다.");
		}
		this.isValid = true;
		this.endAt = LocalDateTime.now();
		this.duration = duration;
	}

	public void setUserMatches(List<UserMatch> userMatches) {
		this.userMatches = userMatches;
	}
}
