package com.qdang.api.match.adapter.in.request;

import com.qdang.api.match.application.port.in.command.StartMatchCommand;
import com.qdang.api.match.domain.MatchTargetScore;
import com.qdang.core.match.MatchType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartMatchRequest {

	@Schema(description = "경기 타입")
	private MatchType matchType;

	@Schema(description = "유저 수, 1~4명")
	@Range(min = 1, max = 4)
	private Integer userCount;

	@Schema(description = "유저 수 크기만큼")
	private List<MatchTargetScoreRequest> matchTargetScoreList;


	public StartMatchCommand toStartMatchCommand() {
		List<MatchTargetScore> matchTargetScores = this.matchTargetScoreList.stream()
			.map(MatchTargetScoreRequest::toMatchTargetScore)
			.collect(Collectors.toList());
		return StartMatchCommand.of(matchType, userCount, matchTargetScores);
	}
}
