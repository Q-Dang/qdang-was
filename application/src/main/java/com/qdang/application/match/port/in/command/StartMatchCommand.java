package com.qdang.application.match.port.in.command;

import com.qdang.application.match.domain.MatchTargetScore;
import com.qdang.application.match.domain.MatchType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StartMatchCommand {

	// Todo: Persistence layer 에도 따로 설정해주기
	private MatchType matchType;
	private Integer userCount;
	private List<MatchTargetScore> matchTargetScoreList;

	public static StartMatchCommand of(MatchType matchType, Integer userCount, List<MatchTargetScore> matchTargetScoreList) {
		return new StartMatchCommand(matchType, userCount, matchTargetScoreList);
	}
}
