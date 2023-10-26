package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.vo.PostInfo;

public interface GetPostDetailUseCase {

	PostInfo getPostDetail(Long postId);
}
