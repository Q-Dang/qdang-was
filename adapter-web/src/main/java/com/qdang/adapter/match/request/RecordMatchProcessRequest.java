package com.qdang.adapter.match.request;

import com.qdang.application.match.domain.TurnType;
import com.qdang.application.match.domain.UserMatchStatus;
import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "턴 기록 api 요청")
public class RecordMatchProcessRequest {

	@Schema(description = "매치 아이디")
	@NotNull(message = "{notNull.match.matchId}")
	private Long matchId;

	@Schema(description = "턴 차례인 유저 아이디")
	@NotNull(message = "{notNull.matchProcess.playerId}")
	private Long playerId;

	@Schema(description = "턴에 소요한 시간, 00:00:00 ~ 23:59:59", example = "00:00:00")
	@NotNull(message = "{notNull.matchProcess.duration}")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime duration;

	@Schema(description = "친 페이스 횟수")
	@NotNull(message = "{notNull.matchProcess.phaseCount}")
	private Integer phaseCount;

	@Schema(description = "페이스별 친 횟수")
	@NotNull(message = "{notNull.matchProcess.turnCount}")
	private Integer turnCount;

	@Schema(description = "총 기록힌 횟수")
	@NotNull(message = "{notNull.matchProcess.processCount}")
	private Integer processCount;

	@Schema(description = "턴에 대한 유저 정보")
	@Size(min = 1, max = 4, message = "{range.size.matchProcess.turnUserProcessList}")
	List<TurnUserProcessDto> turnUserProcessList;

	public RecordMatchProcessCommand toRecordMatchProcessCommand() {
		List<RecordMatchProcessCommand.UserMatchProcessCommand> userMatchProcessCommands =
				turnUserProcessList
						.stream()
						.map(turnUserProcess -> {
							return RecordMatchProcessCommand.UserMatchProcessCommand.of(
									turnUserProcess.getUserId(),
									turnUserProcess.getScore(),
									turnUserProcess.getFinishCushionScore(),
									turnUserProcess.getFinishBankShotScore(),
									turnUserProcess.getRank(),
									turnUserProcess.getStatus(),
									turnUserProcess.getMaxHighRun(),
									turnUserProcess.getHighRun(),
									turnUserProcess.getDeltaScore(),
									turnUserProcess.getTurnType(),
									turnUserProcess.getIsMyTurn(),
									turnUserProcess.getInningCount(),
									turnUserProcess.getSucceedInningCount(),
									turnUserProcess.getFailedInningCount()
							);
						})
						.collect(Collectors.toList());

		return RecordMatchProcessCommand.of(
						matchId,
						playerId,
						duration,
						processCount,
						turnCount,
						phaseCount,
				userMatchProcessCommands
		);
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@Schema(description = "턴에 대한 유저 정보")
	public static class TurnUserProcessDto {

		@Schema(description = "유저 아이디")
		@NotNull(message = "{notNull.user.userId}")
		private Long userId;

		@Schema(description = "점수")
		@NotNull(message = "{notNull.userMatch.score}")
		private Integer score;

		@Schema(description = "쿠션 점수")
		private Integer finishCushionScore;

		@Schema(description = "뱅크샷 점수")
		private Integer finishBankShotScore;

		@Schema(description = "순위")
		@NotNull(message = "{notNull.userMatch.rank}")
		private Integer rank;

		@Schema(description = "유저 상태", example = "NORMAL, CUSHION, BANK_SHOT, END")
		@NotNull(message = "{notNull.userMatch.status}")
		private UserMatchStatus status;

		@Schema(description = "최고 장타")
		@NotNull(message = "{notNull.userMatch.maxHighRun}")
		private Integer maxHighRun;

		@Schema(description = "현재 장타")
		@NotNull(message = "{notNull.userMatch.highRun}")
		private Integer highRun;

		@Schema(description = "점수 편차")
		@NotNull(message = "{notNull.userMatch.deltaScore}")
		private Integer deltaScore;

		@Schema(description = "턴 타입", example = "SUCCESS, FAIL")
		@NotNull(message = "{notNull.userMatch.turnType}")
		private TurnType turnType;

		@Schema(description = "턴 차례 여부", defaultValue = "false")
		@NotNull(message = "{notNull.userMatch.isMyTurn}")
		private Boolean isMyTurn;

		@Schema(description = "이닝 횟수")
		@NotNull(message = "{notNull.userMatch.inningCount}")
		private Integer inningCount;

		@Schema(description = "성공 이닝 횟수")
		@NotNull(message = "{notNull.userMatch.succeedInningCount}")
		private Integer succeedInningCount;

		@Schema(description = "실패 이닝 횟수")
		@NotNull(message = "{notNull.userMatch.failedInningCount}")
		private Integer failedInningCount;
	}
}
