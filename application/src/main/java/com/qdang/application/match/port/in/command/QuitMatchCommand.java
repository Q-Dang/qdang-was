package com.qdang.application.match.port.in.command;

import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuitMatchCommand {

	private Long matchId;
	private Long playerId;
	private LocalTime playTime;
	private List<UserMatchResultCommand> userMatchResultCommand;

	public static QuitMatchCommand of(
			Long matchId,
			Long playerId,
			LocalTime playTime,
			List<UserMatchResultCommand> userMatchResultCommand) {
		return new QuitMatchCommand(
				matchId,
				playerId,
				playTime,
				userMatchResultCommand);
	}
}
