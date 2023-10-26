package com.qdang.adapter.post.request;

import com.qdang.application.noticeboard.port.in.command.LikePostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시글 좋아요하기, 취소하기 요청")
public class LikePostRequest {

	@Schema(description = "게시글 아이디", example = "1")
	@NotNull(message = "{notNull.post.postId}")
	private Long postId;

	@Schema(description = "좋아요 여부", example = "true")
	@NotNull(message = "{notNull.post.like}")
	private Boolean like;

	public LikePostCommand toLikePostCommand(
			Long userId) {
		return LikePostCommand.of(
				userId,
				postId,
				like);
	}
}
