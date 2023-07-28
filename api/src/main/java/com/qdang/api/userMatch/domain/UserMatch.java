package com.qdang.api.userMatch.domain;

import com.qdang.core.match.MatchJpaEntity;
import com.qdang.core.user.UserJpaEntity;
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
	private UserJpaEntity user;
	private MatchJpaEntity match;
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
}
