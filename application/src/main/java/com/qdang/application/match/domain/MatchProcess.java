package com.qdang.application.match.domain;

import com.qdang.application.user.domain.User;
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
	private Match match;
	private User player;
	private LocalTime duration;
	private Integer processCount;
	private Integer turnCount;
	private Integer phaseCount;
	private Boolean isValid;

	public static MatchProcess init(Long id) {
		return MatchProcess.builder()
				.id(id)
				.build();
	}

	public static MatchProcess newInstance(
			Match match,
			User player,
			LocalTime duration,
			Integer processCount,
			Integer turnCount,
			Integer phaseCount,
			Boolean isValid
	) {
		return MatchProcess.builder()
				.match(match)
				.player(player)
				.duration(duration)
				.processCount(processCount)
				.turnCount(turnCount)
				.phaseCount(phaseCount)
				.isValid(isValid)
				.build();
	}
}
