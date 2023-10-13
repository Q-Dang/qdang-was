package com.qdang.adapter.match.request;

import com.qdang.application.match.port.in.command.QuitMatchCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게임 종료 api 요청")
public class QuitMatchRequest {

	@Schema(description = "게임 아이디", example = "1")
	@NotNull(message = "{notNull.match.matchId}")
	private Long matchId;

	@Schema(description = "게임 종료 시간", example = "01:30:00")
	@NotNull(message = "{notNull.match.playTime}")
	private LocalTime playTime;

	@Schema(description = "유저 게임 결과")
	@Size(min = 1, max = 4, message = "{range.size.match.userMatchResults}")
	@Valid
	private List<UserMatchResultDto> userMatchResults;

	public QuitMatchCommand toQuitMatchCommand(Long playerId) {

		return QuitMatchCommand.of(
				matchId,
				playerId,
				playTime,
				userMatchResults
						.stream()
						.map(UserMatchResultDto::toUserMatchResultCommand)
						.collect(Collectors.toList()));
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Schema(description = "유저 게임 결과")
	public static class UserMatchResultDto {

		@Schema(description = "유저 아이디", example = "1")
		@NotNull(message = "{notNull.user.userId}")
		private Long userId;

		@Schema(description = "점수", example = "100")
		@NotNull(message = "{notNull.userMatch.score}")
		private Integer score;

		@Schema(description = "쿠션 점수", example = "30")
		@NotNull(message = "{notNull.userMatch.cushionScore}")
		private Integer cushionScore;

		@Schema(description = "뱅크샷 점수", example = "30")
		@NotNull(message = "{notNull.userMatch.bankShotScore}")
		private Integer bankShotScore;

		@Schema(description = "랭킹", example = "1")
		@NotNull(message = "{notNull.userMatch.rank}")
		@Range(min = 1, max = 4, message = "{range.userMatch.rank}")
		private Integer rank;

		@Schema(description = "최대 장타 점수", example = "50")
		@NotNull(message = "{notNull.userMatch.maxHighRun}")
		private Integer maxHighRun;

		@Schema(description = "에버리지", example = "100")
		@NotNull(message = "{notNull.userMatch.average}")
		private Integer average;

		@Schema(description = "이닝 수", example = "10")
		@NotNull(message = "{notNull.userMatch.inningCount}")
		private Integer inningCount;

		@Schema(description = "성공 이닝 수", example = "7")
		@NotNull(message = "{notNull.userMatch.succeedInningCount}")
		private Integer succeedInningCount;

		@Schema(description = "실패 이닝 수", example = "3")
		@NotNull(message = "{notNull.userMatch.failedInningCount}")
		private Integer failedInningCount;

		@Schema(description = "장타 횟수", example = "5")
		@NotNull(message = "{notNull.userMatch.sluggingCount}")
		private Integer sluggingCount;

		public QuitMatchCommand.UserMatchResultCommand toUserMatchResultCommand() {

			return QuitMatchCommand.UserMatchResultCommand.of(
					userId,
					score,
					cushionScore,
					bankShotScore,
					rank,
					maxHighRun,
					average,
					inningCount,
					succeedInningCount,
					failedInningCount,
					sluggingCount);
		}
	}
}
