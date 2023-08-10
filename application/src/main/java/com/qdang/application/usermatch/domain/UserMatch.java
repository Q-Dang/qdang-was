package com.qdang.application.usermatch.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// TODO: Domain 모델로 고치기
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatch {

	private Long id;
	private Long userId;
	private Long matchId;
	private Integer targetScore;
	private Integer finishCushionTargetScore;
	private Integer finishBankShotTargetScore;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean isDeleted;
	private Integer score;
	private Integer finishCushionScore;
	private Integer finishBankShotScore;
	private Integer ranking;
	private Integer maxHighRun;
	private Integer average;
	private Integer inningCount;
	private Integer succeedInningCount;
	private Integer failedInningCount;

	public static UserMatch of(
		Long userId,
		Long matchId,
		Integer targetScore,
		Integer finishCushionTargetScore,
		Integer finishBankShotTargetScore) {
		return UserMatch.builder()
			.userId(userId)
			.matchId(matchId)
			.targetScore(targetScore)
			.finishCushionTargetScore(finishCushionTargetScore)
			.finishBankShotTargetScore(finishBankShotTargetScore)
			.build();
	}
}
