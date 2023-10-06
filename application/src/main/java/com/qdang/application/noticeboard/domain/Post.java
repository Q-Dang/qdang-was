package com.qdang.application.noticeboard.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {

	private Long id;
	private Long noticeBoardId;
	private Long userId;
	private Boolean isAnonymous;
	private String title;
	private String content;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
