package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import java.util.List;

public interface GetNoticeBoardPinnedListUseCase {

	List<NoticeBoardPinInfo> getNoticeBoardPinnedList(Long userId);
}
