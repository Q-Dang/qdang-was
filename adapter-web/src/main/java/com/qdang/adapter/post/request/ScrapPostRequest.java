package com.qdang.adapter.post.request;

import com.qdang.application.noticeboard.port.in.command.ScrapPostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시글 스크랩하기, 취소하기 요청")
public class ScrapPostRequest {

	@Schema(description = "게시글 아이디", example = "1")
	@NotNull(message = "{notNull.post.postId}")
	private Long postId;

	@Schema(description = "스크랩 여부", example = "true")
	@NotNull(message = "{notNull.post.scrap}")
	private Boolean scrap;

	public ScrapPostCommand toScrapPostCommand(
			Long userId) {
		return ScrapPostCommand.of(
				userId,
				postId,
				scrap);
	}
}
