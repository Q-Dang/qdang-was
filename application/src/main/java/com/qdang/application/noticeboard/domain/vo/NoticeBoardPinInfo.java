package com.qdang.application.noticeboard.domain.vo;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeBoardPinInfo {

	private NoticeBoard noticeBoard;
	private boolean isPinned;

	public static NoticeBoardPinInfo of(
			NoticeBoard noticeBoard,
			Boolean isPinned) {
		return new NoticeBoardPinInfo(
				noticeBoard,
				isPinned);
	}
}
