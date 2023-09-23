package com.qdang.adapter.match.response;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMatchInfoRequest {

	private Long matchId;
	private LocalDateTime createdAt;
	private MatchType matchType;
	private Integer userCount;
	private LocalTime duration;

	/**
	 * 프로필 이미지
	 * 이름
	 * 등수
	 * 점수
	 * 득점 분포도
	 * 	-
	 * 실력 분포도 ?
	 * 	- 장타율
	 *  - 에버리지
	 *  - 하이런
	 *  - 타율
	 *  -
	 *
	 *
	 */

	//



	public static GetMatchInfoRequest from(Match match) {
		return new GetMatchInfoRequest(
				match.getId(),
				match.getCreatedAt(),
				match.getMatchType(),
				match.getUserCount(),
				match.getDuration()
		);
	}
}
