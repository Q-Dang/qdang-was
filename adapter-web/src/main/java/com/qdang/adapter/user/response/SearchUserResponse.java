package com.qdang.adapter.user.response;

import com.qdang.application.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SearchUserResponse {

	@Schema(description = "유저 프로필 리스트")
	private List<UserProfileVo> users;

	public static SearchUserResponse from(List<User> users) {
		return new SearchUserResponse(
				users
						.stream()
						.map(UserProfileVo::from)
						.collect(Collectors.toList()));
	}
}
