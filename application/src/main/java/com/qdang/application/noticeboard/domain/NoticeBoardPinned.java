package com.qdang.application.noticeboard.domain;

import com.qdang.application.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeBoardPinned {

	private Long id;
	private NoticeBoard noticeBoard;
	private User user;

	public static NoticeBoardPinned init(Long id) {
		return NoticeBoardPinned.builder()
				.id(id)
				.build();
	}

	public static NoticeBoardPinned newNoticeBoardPinned(
			NoticeBoard noticeBoard,
			User user) {
		return NoticeBoardPinned.builder()
				.noticeBoard(noticeBoard)
				.user(user)
				.build();
	}
}
