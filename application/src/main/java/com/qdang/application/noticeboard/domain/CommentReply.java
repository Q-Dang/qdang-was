package com.qdang.application.noticeboard.domain;

import com.qdang.application.user.domain.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentReply {

	private Long id;
	private Comment comment;
	private User user;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static CommentReply init(Long id) {
		return CommentReply.builder()
				.id(id)
				.build();
	}
}
