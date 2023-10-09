package com.qdang.adapter.noticeboard.response;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeBoardPinInfoDto {

	private Long id;
	private String boardName;
	private Boolean anonymousPossible;
	private Boolean isAdminPossible;
	private Boolean isManagerPossible;
	private Boolean isMemberPossible;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean isPinned;

	public static NoticeBoardPinInfoDto from(NoticeBoardPinInfo noticeBoardPinInfo) {
		NoticeBoard noticeBoard = noticeBoardPinInfo.getNoticeBoard();
		return NoticeBoardPinInfoDto.builder()
				.id(noticeBoard.getId())
				.boardName(noticeBoard.getBoardName())
				.anonymousPossible(noticeBoard.getAnonymousPossible())
				.isAdminPossible(noticeBoard.getIsAdminPossible())
				.isManagerPossible(noticeBoard.getIsManagerPossible())
				.isMemberPossible(noticeBoard.getIsMemberPossible())
				.createdAt(noticeBoard.getCreatedAt())
				.updatedAt(noticeBoard.getUpdatedAt())
				.isPinned(noticeBoardPinInfo.isPinned())
				.build();
	}
}