package com.qdang.application.match.port.in.command;

import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.match.domain.UserMatchProcess;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordMatchProcessCommand {

	private MatchProcess matchProcess;

	private List<UserMatchProcess> userMatchProcessList;

	public static RecordMatchProcessCommand of(
			MatchProcess matchProcess,
			List<UserMatchProcess> userMatchProcessList) {
		return RecordMatchProcessCommand.builder()
				.matchProcess(matchProcess)
				.userMatchProcessList(userMatchProcessList)
				.build();
	}
}
