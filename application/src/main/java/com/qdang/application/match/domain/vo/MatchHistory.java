package com.qdang.application.match.domain.vo;

import com.qdang.application.match.domain.Match;
import com.qdang.application.usermatch.domain.UserMatch;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchHistory {

	private Match match;
	private UserMatch userMatch;

	public static MatchHistory of(
			Match match,
			UserMatch userMatch) {
		return MatchHistory.builder()
				.match(match)
				.userMatch(userMatch)
				.build();
	}
}
