package com.qdang.application.noticeboard.domain.vo;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeBoardPinInfo implements Comparable<NoticeBoardPinInfo> {

	private NoticeBoard noticeBoard;
	private boolean isPinned;

	public static NoticeBoardPinInfo of(
			NoticeBoard noticeBoard,
			Boolean isPinned) {
		return new NoticeBoardPinInfo(
				noticeBoard,
				isPinned);
	}

	@Override
	public int compareTo(NoticeBoardPinInfo o) {
		if (this.isPinned == o.isPinned) {
			return this.getNoticeBoard().getId().compareTo(o.getNoticeBoard().getId());
		} else {
			if (o.isPinned) {
				return 1;
			}
			return -1;
		}
	}
}
