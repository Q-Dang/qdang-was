package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.NoticeBoardPinned;

public interface DeleteNoticeBoardPinnedPort {

	void delete(NoticeBoardPinned noticeBoardPinned);
}
