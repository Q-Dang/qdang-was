package com.qdang.application.noticeboard.port.in.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WritePostCommand {

	private Long userId;
	private Long noticeBoardId;
	private String hashtag;
	private String title;
	private String content;
	private Boolean isAnonymous;

	public static WritePostCommand of(
			Long userId,
			Long noticeBoardId,
			String hashtag,
			String title,
			String content,
			Boolean isAnonymous) {
		return new WritePostCommand(
				userId,
				noticeBoardId,
				hashtag,
				title,
				content,
				isAnonymous
		);
	}
}
