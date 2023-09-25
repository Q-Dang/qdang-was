package com.qdang.application.match.Vo;

import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchProcessHistory {

	private MatchProcess matchProcess;
	private UserMatchProcess userMatchProcess;

	public static UserMatchProcessHistory of(
			MatchProcess matchProcess,
			UserMatchProcess userMatchProcess) {
		return UserMatchProcessHistory.builder()
				.matchProcess(matchProcess)
				.userMatchProcess(userMatchProcess)
				.build();
	}
}
