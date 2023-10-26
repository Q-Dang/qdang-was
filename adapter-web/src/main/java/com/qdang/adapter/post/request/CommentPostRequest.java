package com.qdang.adapter.post.request;

import com.qdang.application.noticeboard.port.in.command.CommentPostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시글 댓글 달기 요청")
public class CommentPostRequest {

	@Schema(description = "게시글 아이디", example = "1")
	@NotNull(message = "{notNull.post.postId}")
	private Long postId;

	@Schema(description = "댓글 내용", example = "댓글 내용")
	@NotNull(message = "{notNull.comment.content}")
	private String content;

	@Schema(description = "익명 여부", example = "true")
	@NotNull(message = "{notNull.comment.isAnonymous}")
	private Boolean isAnonymous;

	public CommentPostCommand toCommentPostCommand(
			Long userId) {
		return CommentPostCommand.of(
				userId,
				postId,
				content,
				isAnonymous);
	}
}
