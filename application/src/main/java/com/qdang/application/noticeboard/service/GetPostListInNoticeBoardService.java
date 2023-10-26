package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.vo.PostInfo;
import com.qdang.application.noticeboard.port.in.GetPostListInNoticeBoardUseCase;
import com.qdang.application.noticeboard.port.out.LoadCommentPort;
import com.qdang.application.noticeboard.port.out.LoadPostLikesPort;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class GetPostListInNoticeBoardService implements GetPostListInNoticeBoardUseCase {

	private final LoadPostPort loadPostPort;
	private final LoadPostLikesPort loadPostLikesPort;
	private final LoadCommentPort loadCommentPort;

	@Override
	@Transactional(readOnly = true)
	public List<PostInfo> getPostListInNoticeBoard(Long noticeBoardId) {
		List<Post> posts = loadPostPort.loadAllFetchHashtagByNoticeBoardId(noticeBoardId);
		return posts
				.stream()
				.map(post ->
						PostInfo.of(
								post,
								loadPostLikesPort.loadAllByPostId(post.getId()),
								loadCommentPort.loadAllByPostId(post.getId())))
				.collect(Collectors.toList());
	}
}
