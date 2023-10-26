package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import com.qdang.application.noticeboard.port.in.GetNoticeBoardPinnedListUseCase;
import com.qdang.application.noticeboard.port.out.FindNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPort;
import com.qdang.global.usecase.UseCase;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class GetNoticeBoardListService implements GetNoticeBoardPinnedListUseCase {

	private final LoadNoticeBoardPort loadNoticeBoardPort;
	private final FindNoticeBoardPinnedPort findNoticeBoardPinnedPort;

	@Override
	@Transactional(readOnly = true)
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
		return findNoticeBoardPinnedPort
				.findByUserIdAndNoticeBoardId(
						userId,
						noticeBoard.getId())
				.isPresent();
	}
}
