package com.qdang.adapter.post.response;

import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.vo.PostInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시글 상세 조회 응답")
public class GetPostDetailResponse {

	@Schema(description = "게시글 아이디")
	private Long postId;

	@Schema(description = "게시글 작성자 이름")
	private String userName;

	@Schema(description = "게시글 작성자 프로필 이미지")
	private String profileImageUrl;

	@Schema(description = "게시글 생성 시간")
	private LocalDateTime createdAt;

	@Schema(description = "게시글 제목")
	private String title;

	@Schema(description = "게시글 내용")
	private String content;

	@Schema(description = "익명 여부")
	private Boolean isAnonymous;

	@Schema(description = "좋아요 수")
	private Integer likesCount;

	@Schema(description = "댓글 수")
	private Integer commentCount;

	@Schema(description = "댓글 리스트")
	private List<CommentDto> commentList;

	public static GetPostDetailResponse from(PostInfo postInfo) {
		String userName = postInfo
				.getPost()
				.getIsAnonymous() ? "익명" : postInfo.getPost().getUser().getUsername();
		return GetPostDetailResponse.builder()
				.postId(postInfo.getPost().getId())
				.userName(userName)
				.profileImageUrl(postInfo.getPost().getUser().getProfileImage())
				.createdAt(postInfo.getPost().getCreatedAt())
				.title(postInfo.getPost().getTitle())
				.content(postInfo.getPost().getContent())
				.isAnonymous(postInfo.getPost().getIsAnonymous())
				.likesCount(postInfo.getPostLikesList().size())
				.commentCount(postInfo.getComments().size())
				.commentList(
						postInfo.getComments()
								.stream()
								.map(CommentDto::from)
								.collect(java.util.stream.Collectors.toList())
				)
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@Schema(description = "댓글 정보")
	public static class CommentDto {

		@Schema(description = "댓글 아이디")
		private Long commentId;

		@Schema(description = "댓글 작성자 이름")
		private String userName;

		@Schema(description = "댓글 작성자 프로필 이미지")
		private String profileImageUrl;

		@Schema(description = "댓글 내용")
		private String content;

		@Schema(description = "댓글 생성 시간")
		private LocalDateTime createdAt;

		@Schema(description = "익명 여부")
		private Boolean isAnonymous;

		public static CommentDto from(Comment comment) {
			String userName = comment
					.getIsAnonymous() ? "익명" : comment.getUser().getUsername();
			return CommentDto.builder()
					.commentId(comment.getId())
					.userName(userName)
					.profileImageUrl(comment.getUser().getProfileImage())
					.content(comment.getContent())
					.createdAt(comment.getCreatedAt())
					.isAnonymous(comment.getIsAnonymous())
					.build();
		}
	}
}
