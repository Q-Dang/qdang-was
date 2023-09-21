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
	// Todo : 당구장 기능 추가 시
	private String billiardRoom;
	private MatchType matchType;
	private LocalDateTime createdAt;
	private Integer userCount;
	private LocalTime duration;

	private Long playerId;

	/**
	 *
	 */

	/**
	 * 프로필 이미지
	 * 이름
	 * 등수
	 * 점수
	 * 장타율
	 * - 득점 분포도
	 * - ...
	 */

//	public static GetMatchInfoRequest from(Long playerId, Match match) {
//		return new GetMatchInfoRequest(
//				match.getId(),
//				match.getCreatedAt(),
//				match.getMatchType(),
//				match.getUserCount(),
//				match.getDuration()
//		);
//	}
}
