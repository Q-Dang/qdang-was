package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.port.in.command.PinNoticeBoardCommand;

public interface PinNoticeBoardUseCase {

	void pinNoticeBoard(PinNoticeBoardCommand command);
}
