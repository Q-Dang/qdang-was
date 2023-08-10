package com.qdang.application.match.port.in.command;

import com.qdang.application.match.domain.MatchTargetScore;
import com.qdang.application.match.domain.MatchType;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StartMatchCommand {

	private MatchType matchType;
	private Integer userCount;
	private List<MatchTargetScore> matchTargetScoreList;

	public static StartMatchCommand of(
			MatchType matchType,
			Integer userCount,
			List<MatchTargetScore> matchTargetScoreList) {
		return new StartMatchCommand(matchType, userCount, matchTargetScoreList);
	}
}
