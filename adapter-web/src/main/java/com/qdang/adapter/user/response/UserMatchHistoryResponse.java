package com.qdang.adapter.user.response;

import com.qdang.application.match.domain.MatchHistory;
import com.qdang.application.match.domain.MatchType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "유저의 경기 기록")
public class UserMatchHistoryResponse {

	@Schema(description = "경기 ID")
	private Long matchId;

	@Schema(description = "경기 참여 인원")
	private Integer userCount;

	@Schema(description = "경기 타입")
	private MatchType matchType;

	@Schema(description = "경기 생성 시간")
	private LocalDateTime createdAt;

	@Schema(description = "경기 점수")
	private Integer score;

	@Schema(description = "경기 쿠션 점수")
	private Integer finishCushionScore;

	@Schema(description = "경기 뱅크샷 점수")
	private Integer finishBankShotScore;

	@Schema(description = "경기 최대 장타")
	private Integer maxHighRun;

	@Schema(description = "경기 삭제 여부")
	private Boolean isDeleted;

	@Schema(description = "경기 유효 여부 (잘 종료 되었는지)")
	private Boolean isValid;

	@Schema(description = "유저의 경기 순위")
	private Integer ranking;

	public static UserMatchHistoryResponse from(MatchHistory matchHistory) {
		return UserMatchHistoryResponse.builder()
				.matchId(matchHistory.getMatch().getId())
				.userCount(matchHistory.getMatch().getUserCount())
				.matchType(matchHistory.getMatch().getMatchType())
				.createdAt(matchHistory.getMatch().getCreatedAt())
				.score(matchHistory.getUserMatch().getScore())
				.finishCushionScore(matchHistory.getUserMatch().getFinishCushionScore())
				.finishBankShotScore(matchHistory.getUserMatch().getFinishBankShotScore())
				.maxHighRun(matchHistory.getUserMatch().getMaxHighRun())
				.isDeleted(matchHistory.getMatch().getIsDeleted())
				.isValid(matchHistory.getMatch().getIsValid())
				.ranking(matchHistory.getUserMatch().getRanking())
				.build();
	}
}
