package com.qdang.adapter.noticeboard.response;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시판 리스트 조회 응답")
public class GetNoticeBoardListResponse {

	@Schema(description = "게시판 정보 리스트")
	private List<NoticeBoardPinInfoDto> noticeBoardList;

	public static GetNoticeBoardListResponse from(List<NoticeBoardPinInfo> noticeBoardPinInfoList) {
		return GetNoticeBoardListResponse.builder()
				.noticeBoardList(
						noticeBoardPinInfoList
								.stream()
								.map(noticeBoardPinInfo ->
										NoticeBoardPinInfoDto.from(noticeBoardPinInfo))
								.collect(Collectors.toList()))
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@Schema(description = "게시판 정보")
	public static class NoticeBoardPinInfoDto {

		@Schema(description = "게시판 아이디")
		private Long id;

		@Schema(description = "게시판 이름")
		private String boardName;

		@Schema(description = "익명 여부")
		private Boolean anonymousPossible;

		@Schema(description = "관리자 권한 여부")
		private Boolean isAdminPossible;

		@Schema(description = "매니저 권한 여부")
		private Boolean isManagerPossible;

		@Schema(description = "회원 권한 여부")
		private Boolean isMemberPossible;

		@Schema(description = "게시판 생성 시간")
		private LocalDateTime createdAt;

		@Schema(description = "게시판 수정 시간")
		private LocalDateTime updatedAt;

		@Schema(description = "게시판 고정 여부")
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
}
