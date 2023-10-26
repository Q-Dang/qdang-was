package com.qdang.application.noticeboard.domain;

import com.qdang.application.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Scrap {

	private Long id;
	private Post post;
	private User user;

	public static Scrap newScrap(
			Post post,
			User user) {
		return Scrap.builder()
				.post(post)
				.user(user)
				.build();
	}

	public static Scrap init(Long id) {
		return Scrap.builder()
				.id(id)
				.build();
	}
}
