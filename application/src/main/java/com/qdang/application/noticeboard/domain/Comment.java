package com.qdang.application.noticeboard.domain;

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
	private Long userId;
	private Long postId;
	private String content;
	private Boolean isDeleted;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
}
