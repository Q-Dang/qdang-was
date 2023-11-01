package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.port.in.SearchPostUseCase;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class SearchPostService implements SearchPostUseCase {

	private final LoadPostPort loadPostPort;

	@Override
	@Transactional(readOnly = true)
	public List<Post> searchPost(String keyword) {
		return loadPostPort.loadByTitleAndContent(keyword);
	}
}
