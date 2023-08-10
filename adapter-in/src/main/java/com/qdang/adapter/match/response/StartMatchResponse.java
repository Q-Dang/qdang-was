package com.qdang.adapter.match.response;

import com.qdang.application.match.domain.Match;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StartMatchResponse {

	private final Long matchId;
	private final Integer userCount;

	public static StartMatchResponse of(Match match) {
		return new StartMatchResponse(match.getId(), match.getUserCount());
	}

}
