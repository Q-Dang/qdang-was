package com.qdang.application.match.domain;

import com.qdang.application.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchProcess {

	private Long id;
	private User user;
	private MatchProcess matchProcess;
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

	public static UserMatchProcess init(Long id) {
		return UserMatchProcess.builder()
				.id(id)
				.build();
	}

	public static UserMatchProcess newInstance(
			User user,
			MatchProcess matchProcess,
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
			Integer failedInningCount
	) {
		return UserMatchProcess.builder()
				.user(user)
				.matchProcess(matchProcess)
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
