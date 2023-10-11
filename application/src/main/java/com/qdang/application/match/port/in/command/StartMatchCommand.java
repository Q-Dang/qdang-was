package com.qdang.application.match.port.in.command;

import com.qdang.application.match.domain.MatchType;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StartMatchCommand {

	private MatchType matchType;
	private Integer userCount;
	private List<MatchTargetScoreCommand> matchTargetScoreList;

	public static StartMatchCommand of(
			MatchType matchType,
			Integer userCount,
			List<MatchTargetScoreCommand> matchTargetScoreList) {

		return new StartMatchCommand(matchType, userCount, matchTargetScoreList);
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class MatchTargetScoreCommand {

		private Long userId;
		private Integer targetScore;
		private Integer cushionTargetScore;
		private Integer bankShotTargetScore;

		public static MatchTargetScoreCommand of(
				Long userId,
				Integer targetScore,
				Integer cushionTargetScore,
				Integer bankShotTargetScore) {

			return MatchTargetScoreCommand.builder()
					.userId(userId)
					.targetScore(targetScore)
					.cushionTargetScore(cushionTargetScore)
					.bankShotTargetScore(bankShotTargetScore)
					.build();
		}
	}
}
