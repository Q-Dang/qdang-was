package com.qdang.application.match.Vo;

import com.qdang.application.user.domain.User;
import com.qdang.application.usermatch.domain.UserMatch;
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

	// Todo : 모든 UserMatchProcessHistory 들을 리턴하는 것이 아닌, phaseCount 별로 가장 큰 processCount 를 가진 UserMatchProcessHistory 를 리턴하도록 수정
}
