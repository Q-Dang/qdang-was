package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.port.in.GetPostUseCase;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetPostService implements GetPostUseCase {

	private final LoadPostPort loadPostPort;
	@Override
	public Post getPost(Long postId) {
		// 스크랩 정보, 좋아요 정보 해시태그 등을 가져온다.
		return loadPostPort.loadById(postId);
	}
}
