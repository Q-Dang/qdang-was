package com.qdang.application.noticeboard.port.in.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentPostCommand {

	private Long userId;
	private Long postId;
	private String content;
	private Boolean isAnonymous;

	public static CommentPostCommand of(
		Long userId,
		Long postId,
		String content,
		Boolean isAnonymous
	) {
		return new CommentPostCommand(
			userId,
			postId,
			content,
			isAnonymous
		);
	}
}
