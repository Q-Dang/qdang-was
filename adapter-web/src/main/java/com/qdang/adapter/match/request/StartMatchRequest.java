package com.qdang.adapter.match.request;

import com.qdang.application.match.domain.MatchType;
import com.qdang.application.match.port.in.command.StartMatchCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "경기 시작 요청")
public class StartMatchRequest {

	@Schema(description = "경기 타입")
	@NotNull(message = "{match.matchType.notnull}")
	private MatchType matchType;

	@Schema(description = "유저 수, 1~4명")
	@Range(min = 1, max = 4, message = "{match.userCount.range}")
	private Integer userCount;

	@Schema(description = "유저 수 크기만큼")
	@Size(min = 1, max = 4, message = "{match.matchTargetScoreList.size.range}")
	private List<MatchTargetScoreVo> matchTargetScoreList;


	public StartMatchCommand toStartMatchCommand() {
		List<com.qdang.application.match.domain.MatchTargetScore> matchTargetScores = this.matchTargetScoreList.stream()
			.map(MatchTargetScoreVo::toMatchTargetScore)
			.collect(Collectors.toList());
		return StartMatchCommand.of(matchType, userCount, matchTargetScores);
	}
}
