package com.qdang.application.match.port.in.command;

import com.qdang.application.match.domain.TurnType;
import com.qdang.application.match.domain.UserMatchStatus;
import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordMatchProcessCommand {

	private Long matchId;
	private Long playerId;
	private LocalTime duration;
	private Integer processCount;
	private Integer turnCount;
	private Integer phaseCount;
	private Boolean isValid;
	private List<UserMatchProcessCommand> userMatchProcessCommandList;

	public static RecordMatchProcessCommand of(
			Long matchId,
			Long playerId,
			LocalTime duration,
			Integer processCount,
			Integer turnCount,
			Integer phaseCount,
			List<UserMatchProcessCommand> userMatchProcessCommandList) {

		return RecordMatchProcessCommand.builder()
				.matchId(matchId)
				.playerId(playerId)
				.duration(duration)
				.processCount(processCount)
				.turnCount(turnCount)
				.phaseCount(phaseCount)
				.userMatchProcessCommandList(userMatchProcessCommandList)
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class UserMatchProcessCommand {

		private Long userId;
		private Integer score;
		private Integer finishCushionScore;
		private Integer finishBankShotScore;
		private Integer ranking;
		private UserMatchStatus status;
		private Integer maxHighRun;
		private Integer highRun;
		private Integer deltaScore;
		private TurnType turnType;
		private Boolean isMyTurn;
		private Integer inningCount;
		private Integer succeedInningCount;
		private Integer failedInningCount;

		public static UserMatchProcessCommand of(
				Long userId,
				Integer score,
				Integer finishCushionScore,
				Integer finishBankShotScore,
				Integer ranking,
				UserMatchStatus status,
				Integer maxHighRun,
				Integer highRun,
				Integer deltaScore,
				TurnType turnType,
				Boolean isMyTurn,
				Integer inningCount,
				Integer succeedInningCount,
				Integer failedInningCount) {

			return UserMatchProcessCommand.builder()
					.userId(userId)
					.score(score)
					.finishCushionScore(finishCushionScore)
					.finishBankShotScore(finishBankShotScore)
					.ranking(ranking)
					.status(status)
					.maxHighRun(maxHighRun)
					.highRun(highRun)
					.deltaScore(deltaScore)
					.turnType(turnType)
					.isMyTurn(isMyTurn)
					.inningCount(inningCount)
					.succeedInningCount(succeedInningCount)
					.failedInningCount(failedInningCount)
					.build();
		}
	}
}
