package com.qdang.application.match.domain.vo;

import com.qdang.application.user.domain.User;
import com.qdang.application.match.domain.UserMatch;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchDetail {

	private User user;
	private UserMatch userMatch;
	private List<UserMatchProcessHistory> UserMatchProcessHistoryList;

	public static UserMatchDetail of(
			User user,
			UserMatch userMatch,
			List<UserMatchProcessHistory> userMatchProcessHistories) {
		return UserMatchDetail.builder()
				.user(user)
				.userMatch(userMatch)
				.UserMatchProcessHistoryList(userMatchProcessHistories)
				.build();
	}
}
