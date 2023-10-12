package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import com.qdang.application.noticeboard.port.in.PinNoticeBoardUseCase;
import com.qdang.application.noticeboard.port.in.command.PinNoticeBoardCommand;
import com.qdang.application.noticeboard.port.out.DeleteNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPort;
import com.qdang.application.noticeboard.port.out.SaveNoticeBoardPinnedPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class PinNoticeBoardService implements PinNoticeBoardUseCase {

	private final LoadNoticeBoardPort loadNoticeBoardPort;
	private final LoadUserPort loadUserPort;
	private final LoadNoticeBoardPinnedPort loadNoticeBoardPinnedPort;
	private final SaveNoticeBoardPinnedPort saveNoticeBoardPinnedPort;
	private final DeleteNoticeBoardPinnedPort deleteNoticeBoardPinnedPort;

	@Override
	public void pinNoticeBoard(PinNoticeBoardCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		NoticeBoard noticeBoard = loadNoticeBoardPort.loadById(command.getNoticeBoardId());
		Optional<NoticeBoardPinned> noticeBoardPinned = loadNoticeBoardPinnedPort.findByUserIdAndNoticeBoardId(
				command.getUserId(),
				command.getNoticeBoardId());
		if (pinnedNoticeBoard(command)) {
			if (!noticeBoardPinned.isPresent()) {
				NoticeBoardPinned newNoticeBoardPinned = NoticeBoardPinned.newNoticeBoardPinned(
						noticeBoard,
						user);
				saveNoticeBoardPinnedPort.save(newNoticeBoardPinned);
			}
		}
		if (unPinnedNoticeBoard(command)){
			if (noticeBoardPinned.isPresent()) {
				deleteNoticeBoardPinnedPort.delete(noticeBoardPinned.get());
			}
		}
	}

	private static Boolean pinnedNoticeBoard(PinNoticeBoardCommand command) {
		return command.getPin();
	}

	private static Boolean unPinnedNoticeBoard(PinNoticeBoardCommand command) {
		return !command.getPin();
	}
}