package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import com.qdang.application.noticeboard.port.in.GetNoticeBoardPinnedListUseCase;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetNoticeBoardListService implements GetNoticeBoardPinnedListUseCase {

	private final LoadNoticeBoardPort loadNoticeBoardPort;
	private final LoadNoticeBoardPinnedPort loadNoticeBoardPinnedPort;

	@Override
	public List<NoticeBoardPinInfo> getNoticeBoardPinnedList(Long userId) {
		List<NoticeBoard> noticeBoards = loadNoticeBoardPort.loadAllNotDeleted();
		return noticeBoards
				.stream()
				.map(noticeBoard -> {
					Optional<NoticeBoardPinned> pinned =
							loadNoticeBoardPinnedPort
									.findByUserIdAndNoticeBoardId(
											userId,
											noticeBoard.getId());
					return NoticeBoardPinInfo.of(
							noticeBoard,
							isPinned(pinned));
				})
				.collect(Collectors.toList());
	}

	private boolean isPinned(Optional<NoticeBoardPinned> noticeBoardPinned) {
		return noticeBoardPinned.isPresent();
	}
}
