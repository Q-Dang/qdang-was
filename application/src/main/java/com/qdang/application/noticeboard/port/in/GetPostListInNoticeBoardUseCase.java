package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.vo.PostInfo;
import java.util.List;

public interface GetPostInNoticeBoardUseCase {

	List<PostInfo> getPostInNoticeBoard(Long noticeBoardId);
}
