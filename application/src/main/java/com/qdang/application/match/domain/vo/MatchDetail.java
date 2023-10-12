package com.qdang.application.match.domain.vo;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.match.domain.UserMatch;
import com.qdang.application.match.domain.UserMatchProcess;
import com.qdang.application.user.domain.User;
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
	private List<UserMatchHistory> userMatchHistoryList;

	public static MatchDetail of(
			Match match,
			List<UserMatchHistory> userMatchHistoryList) {
		return MatchDetail.builder()
				.match(match)
				.userMatchHistoryList(userMatchHistoryList)
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class UserMatchHistory {

		private User user;
		private UserMatch userMatch;
		private List<UserMatchProcessHistory> UserMatchProcessHistoryList;

		public static UserMatchHistory of(
				User user,
				UserMatch userMatch,
				List<UserMatchProcessHistory> userMatchProcessHistories) {
			return UserMatchHistory.builder()
					.user(user)
					.userMatch(userMatch)
					.UserMatchProcessHistoryList(userMatchProcessHistories)
					.build();
		}

		@Getter
		@Builder
		@AllArgsConstructor(access = AccessLevel.PRIVATE)
		public static class UserMatchProcessHistory {

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

	}
}
