package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.domain.vo.PostInfo;
import com.qdang.application.noticeboard.port.in.GetPostDetailUseCase;
import com.qdang.application.noticeboard.port.out.LoadCommentPort;
import com.qdang.application.noticeboard.port.out.LoadPostLikesPort;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class GetPostDetailService implements GetPostDetailUseCase {

	private final LoadPostPort loadPostPort;
	private final LoadPostLikesPort loadPostLikesPort;
	private final LoadCommentPort loadCommentPort;

	@Override
	@Transactional(readOnly = true)
	public PostInfo getPostDetail(Long postId) {
		Post post = loadPostPort.loadFetchHashtagById(postId);
		List<PostLikes> postLikes = loadPostLikesPort.loadAllByPostId(post.getId());
		List<Comment> comments = loadCommentPort.loadAllByPostId(post.getId());
		return PostInfo.of(post, postLikes, comments);
	}
}
