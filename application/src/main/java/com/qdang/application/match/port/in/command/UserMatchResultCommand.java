package com.qdang.application.match.port.in.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchResultCommand {

	private Long userId;
	private Integer score;
	private Integer cushionScore;
	private Integer bankShotScore;
	private Integer ranking;
	private Integer maxHighRun;
	private Integer average;
	private Integer inningCount;
	private Integer succeedInningCount;
	private Integer failedInningCount;
	private Integer sluggingCount;

	public static UserMatchResultCommand of(
			Long userId,
			Integer score,
			Integer cushionScore,
			Integer bankShotScore,
			Integer rank,
			Integer maxHighRun,
			Integer average,
			Integer inning,
			Integer succeedInningCount,
			Integer failedInningCount,
			Integer sluggingCount) {
		return new UserMatchResultCommand(
				userId,
				score,
				cushionScore,
				bankShotScore,
				rank,
				maxHighRun,
				average,
				inning,
				succeedInningCount,
				failedInningCount,
				sluggingCount);
	}
}
