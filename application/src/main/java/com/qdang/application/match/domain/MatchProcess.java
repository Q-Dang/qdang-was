package com.qdang.application.matchprocess.domain;

import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchProcess {

	private Long id;
	private Long matchId;
	private Long playerId;
	private LocalTime duration;
	private Integer processCount;
	private Integer turnCount;
	private Integer phaseCount;
	private Boolean isValid;

	public static MatchProcess newMatchProcess(
			Long matchId,
			Long playerId,
			LocalTime duration,
			Integer processCount,
			Integer turnCount,
			Integer phaseCount,
			Boolean isValid
	) {
		return MatchProcess.builder()
				.matchId(matchId)
				.playerId(playerId)
				.duration(duration)
				.processCount(processCount)
				.turnCount(turnCount)
				.phaseCount(phaseCount)
				.isValid(isValid)
				.build();
	}
}
