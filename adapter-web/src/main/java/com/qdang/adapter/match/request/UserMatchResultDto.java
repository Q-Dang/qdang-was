package com.qdang.adapter.match.request;

import com.qdang.application.match.port.in.command.UserMatchResultCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "유저 게임 결과")
public class UserMatchResultDto {

	@Schema(description = "유저 아이디", example = "1")
	@NotNull(message = "{user.userId.notNull}")
	private Long userId;

	@Schema(description = "점수", example = "100")
	@NotNull(message = "{userMatch.score.notnull}")
	private Integer score;

	@Schema(description = "쿠션 점수", example = "30")
	@NotNull(message = "{userMatch.cushionScore.notnull}")
	private Integer cushionScore;

	@Schema(description = "뱅크샷 점수", example = "30")
	@NotNull(message = "{userMatch.bankShotScore.notnull}")
	private Integer bankShotScore;

	@Schema(description = "랭킹", example = "1")
	@NotNull(message = "{userMatch.rank.notnull}")
	@Range(min = 1, max = 4, message = "{userMatch.rank.range}")
	private Integer rank;

	@Schema(description = "최대 장타 점수", example = "50")
	@NotNull(message = "{userMatch.maxHighRun.notnull}")
	private Integer maxHighRun;

	@Schema(description = "에버리지", example = "100")
	@NotNull(message = "{userMatch.average.notnull}")
	private Integer average;

	@Schema(description = "이닝 수", example = "10")
	@NotNull(message = "{userMatch.inningCount.notnull}")
	private Integer inningCount;

	@Schema(description = "성공 이닝 수", example = "7")
	@NotNull(message = "{userMatch.succeedInningCount.notnull}")
	private Integer succeedInningCount;

	@Schema(description = "실패 이닝 수", example = "3")
	@NotNull(message = "{userMatch.failedInningCount.notnull}")
	private Integer failedInningCount;

	@Schema(description = "장타 횟수", example = "5")
	@NotNull(message = "{userMatch.sluggingCount.notnull}")
	private Integer sluggingCount;

	public UserMatchResultCommand toUserMatchResultCommand() {
		return UserMatchResultCommand.of(
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