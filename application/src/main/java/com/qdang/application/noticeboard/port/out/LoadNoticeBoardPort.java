package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import java.util.List;

public interface LoadNoticeBoardPort {

	NoticeBoard loadById(Long id);

	List<NoticeBoard> loadAllNotDeleted();
}
