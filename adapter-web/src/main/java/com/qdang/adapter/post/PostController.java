package com.qdang.adapter.post;

import com.qdang.adapter.post.request.CommentPostRequest;
import com.qdang.adapter.post.request.LikePostRequest;
import com.qdang.adapter.post.request.ScrapPostRequest;
import com.qdang.adapter.post.request.WritePostRequest;
import com.qdang.adapter.post.response.GetPostDetailResponse;
import com.qdang.application.noticeboard.port.in.CommentPostUseCase;
import com.qdang.application.noticeboard.port.in.GetUserPostDetailUseCase;
import com.qdang.application.noticeboard.port.in.LikePostUseCase;
import com.qdang.application.noticeboard.port.in.ScrapPostUseCase;
import com.qdang.application.noticeboard.port.in.WritePostUseCase;
import com.qdang.application.user.domain.User;
import com.qdang.global.http.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@V1
@WebAdapter
@RequiredArgsConstructor
class PostController implements PostWebAdapter {

	private final GetUserPostDetailUseCase getUserPostDetailUseCase;
	private final WritePostUseCase writePostUseCase;
	private final ScrapPostUseCase scrapPostUseCase;
	private final LikePostUseCase likePostUseCase;
	private final CommentPostUseCase commentPostUseCase;

	@Override
	public ResponseEntity<GetPostDetailResponse> getPostDetail(
			User user,
			Long postId
	) {
		GetPostDetailResponse response =
				GetPostDetailResponse.from(
						getUserPostDetailUseCase.getUserPostDetail(user.getId(), postId));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<Void> writePost(
			User user,
			WritePostRequest request
	) {
		writePostUseCase.writePost(
				request.toWritePostCommand(user.getId()));
		return HttpResponse.success(
				SuccessType.CREATE_RESOURCE_SUCCESS);
	}

	@Override
	public ResponseEntity<Void> scrapPost(
			User user,
			ScrapPostRequest request
	) {
		scrapPostUseCase.scrapPost(
				request.toScrapPostCommand(user.getId()));
		return HttpResponse.success(
				SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

	@Override
	public ResponseEntity<Void> likePost(
			User user,
			LikePostRequest request
	) {
		likePostUseCase.likePost(
				request.toLikePostCommand(user.getId()));
		return HttpResponse.success(
				SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

	@Override
	public ResponseEntity<Void> commentPost(
			User user,
			CommentPostRequest request) {
		commentPostUseCase.commentPost(
				request.toCommentPostCommand(user.getId()));
		return HttpResponse.success(
				SuccessType.CREATE_RESOURCE_SUCCESS);
	}

	@Override
	public ResponseEntity<Void> searchPost(String keyword) {
		return null;
	}

}
