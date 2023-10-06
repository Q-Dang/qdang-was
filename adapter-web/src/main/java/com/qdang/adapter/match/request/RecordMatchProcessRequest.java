package com.qdang.adapter.match.request;

import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.match.domain.UserMatchProcess;
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
	@NotNull(message = "{match.matchId.notnull}")
	private Long matchId;

	@Schema(description = "턴 차례인 유저 아이디")
	@NotNull(message = "{matchProcess.playerId.notnull}")
	private Long playerId;

	@Schema(description = "턴에 소요한 시간, 00:00:00 ~ 23:59:59", example = "00:00:00")
	@NotNull(message = "{matchProcess.duration.notnull}")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime duration;

	@Schema(description = "친 페이스 횟수")
	@NotNull(message = "{matchProcess.phaseCount.notnull}")
	private Integer phaseCount;

	@Schema(description = "페이스별 친 횟수")
	@NotNull(message = "{matchProcess.turnCount.notnull}")
	private Integer turnCount;

	@Schema(description = "총 기록힌 횟수")
	@NotNull(message = "{matchProcess.processCount.notnull}")
	private Integer processCount;

	@Schema(description = "턴에 대한 유저 정보")
	@Size(min = 1, max = 4, message = "{matchProcess.turnUserProcessList.size.range}")
	List<TurnUserProcessDto> turnUserProcessList;

	public RecordMatchProcessCommand toRecordMatchProcessCommand() {
		List<UserMatchProcess> userMatchProcesses = turnUserProcessList.stream()
				.map(turnUserProcess -> {
					return UserMatchProcess.of(
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
				MatchProcess.newMatchProcess(
						matchId,
						playerId,
						duration,
						processCount,
						turnCount,
						phaseCount,
						true
				),
				userMatchProcesses
		);
	}
}
