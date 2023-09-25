package com.qdang.adapter.match.response;

import com.qdang.application.match.Vo.MatchDetail;
import com.qdang.application.match.domain.MatchType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMatchInfoResponse {

	private Long matchId;
	private String billiardRoom;
	private MatchType matchType;
	private LocalDateTime createdAt;
	private Integer userCount;
	private LocalTime duration;
	private Long playerId;
	private List<UserMatchDetailDto> userMatchDetailList;

	public static GetMatchInfoResponse of(Long playerId, MatchDetail matchDetail) {
		return GetMatchInfoResponse.builder()
				.matchId(matchDetail.getMatch().getId())
				// Todo : billiardRoom 이름 가져오기
//				.billiardRoom(matchDetail.getMatch().getBilliardRoom())
				.billiardRoom("Temp Billiard Room Name")
				.matchType(matchDetail.getMatch().getMatchType())
				.createdAt(matchDetail.getMatch().getCreatedAt())
				.userCount(matchDetail.getMatch().getUserCount())
				.duration(matchDetail.getMatch().getDuration())
				.playerId(playerId)
				.userMatchDetailList(
						matchDetail
								.getUserMatchDetailList()
								.stream()
								.map(UserMatchDetailDto::from)
								.collect(Collectors.toList()))
				.build();
	}
}
