package com.qdang.application.match.domain.vo;

import com.qdang.application.match.domain.Match;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchDetail {

	private Match match;
	private List<UserMatchDetail> userMatchDetailList;

	public static MatchDetail of(
			Match match,
			List<UserMatchDetail> userMatchDetailList) {
		return MatchDetail.builder()
				.match(match)
				.userMatchDetailList(userMatchDetailList)
				.build();
	}
}
