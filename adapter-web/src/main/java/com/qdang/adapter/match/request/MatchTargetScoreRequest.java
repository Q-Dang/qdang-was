package com.qdang.adapter.match.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "경기 목표 점수")
public class MatchTargetScoreRequest {

	@Schema(description = "게임에 참여하는 유저 아이디")
	@NotNull(message = "{user.userId.notNull}")
	private Long userId;

	@Schema(description = "목표 점수")
	@NotNull(message = "{userMatch.targetScore.notNull}")
	private Integer targetScore;

	@Schema(description = "쿠션 목표 점수")
	private Integer cushionTargetScore;

	@Schema(description = "뱅크샷 목표 점수")
	private Integer bankShotTargetScore;

	public com.qdang.application.match.domain.MatchTargetScore toMatchTargetScore() {
		return com.qdang.application.match.domain.MatchTargetScore.of(
			userId,
			targetScore,
			cushionTargetScore,
			bankShotTargetScore);
	}
}
