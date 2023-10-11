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
public class Comment {
	private Long id;
	private User user;
	private Post post;
	private String content;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static Comment init(Long id) {
		return Comment.builder()
				.id(id)
				.build();
	}
}
