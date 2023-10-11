package com.qdang.application.noticeboard.domain;

import com.qdang.application.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostLikes {

	private Long id;
	private Post post;
	private User user;

	public static PostLikes init(Long id) {
		return PostLikes.builder()
				.id(id)
				.build();
	}
}
