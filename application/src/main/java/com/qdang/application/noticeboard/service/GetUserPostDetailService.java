package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.domain.Scrap;
import com.qdang.application.noticeboard.domain.vo.UserPostDetail;
import com.qdang.application.noticeboard.port.in.GetUserPostDetailUseCase;
import com.qdang.application.noticeboard.port.out.LoadCommentPort;
import com.qdang.application.noticeboard.port.out.LoadPostLikesPort;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.application.noticeboard.port.out.LoadScrapPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class GetUserPostDetailService implements GetUserPostDetailUseCase {

	private final LoadPostPort loadPostPort;
	private final LoadPostLikesPort loadPostLikesPort;
	private final LoadCommentPort loadCommentPort;
	private final LoadScrapPort loadScrapPort;

	@Override
	@Transactional(readOnly = true)
	public UserPostDetail getUserPostDetail(Long userId, Long postId) {
		Post post = loadPostPort.loadFetchHashtagById(postId);
		List<PostLikes> postLikesList = loadPostLikesPort.loadAllByPostId(post.getId());
		List<Comment> comments = loadCommentPort.loadAllByPostId(post.getId());
		List<Scrap> scraps = loadScrapPort.loadAllByPostId(post.getId());
		return UserPostDetail.of(
				post,
				postLikesList,
				comments,
				scraps,
				getUserLikedPost(postLikesList, userId),
				getUserScrappedPost(scraps, userId));
	}

	private boolean getUserScrappedPost(List<Scrap> scraps, Long userId) {
		for (Scrap scrap : scraps) {
			if (scrap.getUser().getId().equals(userId)) {
				return true;
			}
		}
		return false;
	}

	private boolean getUserLikedPost(List<PostLikes> postLikesList, Long userId) {
		for (PostLikes postLikes : postLikesList) {
			if (postLikes.getUser().getId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
}
