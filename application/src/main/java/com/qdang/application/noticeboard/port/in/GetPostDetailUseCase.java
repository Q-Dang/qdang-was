package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.Post;

public interface GetPostUseCase {

	Post getPost(Long postId);
}
