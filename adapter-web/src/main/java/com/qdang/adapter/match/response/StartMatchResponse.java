package com.qdang.adapter.match.response;

import com.qdang.application.match.domain.Match;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StartMatchResponse {

	@Schema(description = "게임 아이디")
	private final Long matchId;

	@Schema(description = "게임 참여 인원")
	private final Integer userCount;

	public static StartMatchResponse from(Match match) {
		return new StartMatchResponse(match.getId(), match.getUserCount());
	}
}
