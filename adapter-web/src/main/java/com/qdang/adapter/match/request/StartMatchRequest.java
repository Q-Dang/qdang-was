package com.qdang.adapter.match.request;

import com.qdang.application.match.domain.MatchType;
import com.qdang.application.match.port.in.command.StartMatchCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "경기 시작 요청")
public class StartMatchRequest {

	@Schema(description = "경기 타입")
	@NotNull(message = "{match.matchType.notnull}")
	private MatchType matchType;

	@Schema(description = "유저 수, 1~4명")
	@NotNull(message = "{match.userCount.notnull}")
	@Range(min = 1, max = 4, message = "{match.userCount.range}")
	private Integer userCount;

	@Schema(description = "유저 수 크기만큼")
	@Size(min = 1, max = 4, message = "{match.matchTargetScoreList.size.range}")
	private List<MatchTargetScoreDto> matchTargetScoreList;


	public StartMatchCommand toStartMatchCommand() {

		List<StartMatchCommand.MatchTargetScoreCommand> matchTargetScores =
				this.matchTargetScoreList
						.stream()
						.map(matchTargetScoreDto -> {
							return StartMatchCommand.MatchTargetScoreCommand.of(
									matchTargetScoreDto.getUserId(),
									matchTargetScoreDto.getTargetScore(),
									matchTargetScoreDto.getCushionTargetScore(),
									matchTargetScoreDto.getBankShotTargetScore()
							);
						})
						.collect(Collectors.toList());

		return StartMatchCommand.of(
				matchType,
				userCount,
				matchTargetScores);
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Schema(description = "경기 목표 점수")
	public static class MatchTargetScoreDto {

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
	}
}
