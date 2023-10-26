package com.qdang.application.noticeboard.port.in.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LikePostCommand {

	private Long userId;
	private Long postId;
	private Boolean like;

	public static LikePostCommand of(
			Long userId,
			Long postId,
			Boolean like) {
		return new LikePostCommand(
				userId,
				postId,
				like);
	}
}
