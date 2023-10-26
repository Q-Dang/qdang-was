package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.vo.PostInfo;
import java.util.List;

public interface GetPostListInNoticeBoardUseCase {

	List<PostInfo> getPostListInNoticeBoard(Long noticeBoardId);
}
