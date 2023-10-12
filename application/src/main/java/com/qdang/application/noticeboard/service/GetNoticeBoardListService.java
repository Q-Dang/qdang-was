package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import com.qdang.application.noticeboard.port.in.GetNoticeBoardPinnedListUseCase;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPort;
import com.qdang.global.usecase.UseCase;
import java.util.Collections;
import java.util.List;
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
		List<NoticeBoardPinInfo> noticeBoardPinInfos = noticeBoards
				.stream()
				.map(noticeBoard ->
						NoticeBoardPinInfo.of(
								noticeBoard,
								isPinned(userId, noticeBoard)))
				.collect(Collectors.toList());
		Collections.sort(noticeBoardPinInfos);
		return noticeBoardPinInfos;
	}

	private boolean isPinned(Long userId, NoticeBoard noticeBoard) {
		return loadNoticeBoardPinnedPort
				.findByUserIdAndNoticeBoardId(
						userId,
						noticeBoard.getId())
				.isPresent();
	}
}
