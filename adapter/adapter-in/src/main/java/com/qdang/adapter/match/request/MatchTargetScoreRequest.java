package com.qdang.adapter.match.request;

import com.qdang.application.match.domain.MatchTargetScore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchTargetScoreRequest {

	@Schema(description = "게임에 참여하는 유저 아이디")
	private Long userId;
	@Schema(description = "목표 점수")
	private Integer targetScore;
	@Schema(description = "쿠션 목표 점수")
	private Integer cushionTargetScore;
	@Schema(description = "뱅크샷 목표 점수")
	private Integer bankShotTargetScore;

	public MatchTargetScore toMatchTargetScore() {
		return MatchTargetScore.of(
			userId,
			targetScore,
			cushionTargetScore,
			bankShotTargetScore);
	}
}
