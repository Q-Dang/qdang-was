package com.qdang.adapter.match.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "턴 기록 api 요청")
public class RecordTurnRequest {

	@Schema(description = "매치 아이디")
	@NotNull(message = "{match.matchId.notnull}")
	private Long matchId;

	@Schema(description = "턴 차례인 유저 아이디")
	@NotNull(message = "{matchProcess.playUserId.notnull}")
	private Long playUserId;

	@Schema(description = "턴에 소요한 시간", example = "00:00:00")
	@NotNull(message = "{matchProcess.duration.notnull}")
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
	List<TurnUserProcess> turnUserProcessList;
}
