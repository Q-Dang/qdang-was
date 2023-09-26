package com.qdang.application.match.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchTargetScore {

	private Long userId;
	private Integer targetScore;
	private Integer cushionTargetScore;
	private Integer bankShotTargetScore;

	public static MatchTargetScore of(
			Long userId,
			Integer targetScore,
			Integer cushionTargetScore,
			Integer bankShotTargetScore) {
		return MatchTargetScore.builder()
				.userId(userId)
				.targetScore(targetScore)
				.cushionTargetScore(cushionTargetScore)
				.bankShotTargetScore(bankShotTargetScore)
				.build();
	}
}
