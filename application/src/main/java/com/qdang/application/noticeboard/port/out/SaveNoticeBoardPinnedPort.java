package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.NoticeBoardPinned;

public interface SaveNoticeBoardPinnedPort {

	NoticeBoardPinned save(NoticeBoardPinned noticeBoardPinned);
}
