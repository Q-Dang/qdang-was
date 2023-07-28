package com.qdang.api.match.application.port.in.command;

import com.qdang.api.match.domain.MatchTargetScore;
import com.qdang.core.match.MatchType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StartMatchCommand {

	private MatchType matchType;
	private Integer userCount;
	private List<MatchTargetScore> matchTargetScoreList;

	public static StartMatchCommand of(MatchType matchType, Integer userCount, List<MatchTargetScore> matchTargetScoreList) {
		return new StartMatchCommand(matchType, userCount, matchTargetScoreList);
	}
}
