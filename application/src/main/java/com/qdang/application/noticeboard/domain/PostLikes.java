package com.qdang.application.noticeboard.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostLikes {

	private Long id;
	private Long postId;
	private Long userId;
}
