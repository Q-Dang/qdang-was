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

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게임 종료 api 요청")
public class QuitMatchRequest {

	@Schema(description = "게임 아이디", example = "1")
	@NotNull(message = "{match.matchId.notnull}")
	private Long matchId;

	@Schema(description = "게임 종료 시간", example = "01:30:00")
	@NotNull(message = "{match.playTime.notnull}")
	private LocalTime playTime;

	@Schema(description = "유저 게임 결과")
	@Size(min = 1, max = 4, message = "{match.userMatchResults.size.range}")
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
}
