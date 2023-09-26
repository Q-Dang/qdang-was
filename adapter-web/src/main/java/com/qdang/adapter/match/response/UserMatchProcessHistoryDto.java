package com.qdang.adapter.match.response;

import com.qdang.application.match.domain.vo.UserMatchProcessHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchProcessHistoryDto {

	private Integer score;
	private Integer phaseCount;
	private Integer processCount;

	public static UserMatchProcessHistoryDto from(
			UserMatchProcessHistory userMatchProcessHistory) {
		return new UserMatchProcessHistoryDto(
				userMatchProcessHistory.getUserMatchProcess().getScore(),
				userMatchProcessHistory.getMatchProcess().getPhaseCount(),
				userMatchProcessHistory.getMatchProcess().getProcessCount()
		);
	}

}
