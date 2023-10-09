package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import java.util.List;

public interface LoadNoticeBoardPort {

	List<NoticeBoard> loadAllNotDeleted();
}
