package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.vo.UserPostDetail;

public interface GetUserPostDetailUseCase {

	UserPostDetail getUserPostDetail(Long userId, Long postId);
}
