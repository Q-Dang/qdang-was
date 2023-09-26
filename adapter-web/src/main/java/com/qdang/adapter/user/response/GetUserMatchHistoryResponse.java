package com.qdang.adapter.user.response;

import com.qdang.application.match.domain.vo.MatchHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "유저 경기 기록 조회 응답")
public class GetUserMatchHistoryResponse {

	@Schema(description = "유저 경기 기록 리스트")
	List<UserMatchHistoryDto> userMatchHistoryList;

	public static GetUserMatchHistoryResponse from(List<MatchHistory> matchHistoryList) {
		return new GetUserMatchHistoryResponse(
				matchHistoryList
						.stream()
						.map(UserMatchHistoryDto::from)
						.collect(Collectors.toList()));
	}
}
