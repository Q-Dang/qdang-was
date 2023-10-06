package com.qdang.application.noticeboard.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeBoard {

	private Long id;
	private String boardName;
	private Boolean anonymousPossible;
	private Boolean isAdminPossible;
	private Boolean isManagerPossible;
	private Boolean isMemberPossible;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
