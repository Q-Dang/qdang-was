package com.qdang.application.noticeboard.port.in.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapPostCommand {

	Long userId;
	Long postId;
	Boolean scrap;

	public static ScrapPostCommand of(
			Long userId,
			Long postId,
			Boolean scrap) {
		return new ScrapPostCommand(
				userId,
				postId,
				scrap);
	}
}
