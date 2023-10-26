package com.qdang.adapter.post.request;

import com.qdang.application.noticeboard.port.in.command.WritePostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시글 쓰기 요청")
public class WritePostRequest {

	@Schema(description = "게시판 아이디", example = "1")
	@NotNull(message = "{notNull.noticeBoard.noticeBoardId}")
	private Long noticeBoardId;

	@Schema(description = "해시태그", example = "해시태그내용")
	private String hashtag;

	@Schema(description = "게시글 제목", example = "게시글 제목")
	private String title;

	@Schema(description = "게시글 내용", example = "게시글 내용")
	private String content;

	@Schema(description = "익명 여부", example = "true")
	@NotNull(message = "{notNull.noticeBoard.isAnonymous}")
	private Boolean isAnonymous;

	public WritePostCommand toWritePostCommand(Long userId) {
		return WritePostCommand.of(
				userId,
				noticeBoardId,
				hashtag,
				title,
				content,
				isAnonymous);
	}
}
