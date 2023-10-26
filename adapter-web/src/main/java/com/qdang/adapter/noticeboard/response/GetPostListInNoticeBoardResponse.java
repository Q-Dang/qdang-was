package com.qdang.adapter.noticeboard.response;

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
@Schema(description = "게시글 리스트 조회 응답")
public class GetPostListInNoticeBoardResponse {

	@Schema(description = "게시글 리스트")
	private List<PostSummaryDto> postSummaryList;

	public static GetPostListInNoticeBoardResponse from(List<PostInfo> postInfoList) {
		return GetPostListInNoticeBoardResponse.builder()
				.postSummaryList(
						postInfoList
								.stream()
								.map(PostSummaryDto::from)
								.collect(java.util.stream.Collectors.toList())
				)
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class PostSummaryDto {

		@Schema(description = "게시글 아이디")
		private Long postId;

		@Schema(description = "게시글 제목")
		private String title;

		@Schema(description = "게시글 내용")
		private String content;

		@Schema(description = "게시글 작성자 이름")
		private String userName;

		@Schema(description = "게시글 생성 시간")
		private LocalDateTime createdAt;

		@Schema(description = "익명 여부")
		private Boolean isAnonymous;

		@Schema(description = "좋아요 수")
		private Integer likesCount;

		@Schema(description = "댓글 수")
		private Integer commentCount;

		@Schema(description = "해시태그")
		private String hashtag;

		public static PostSummaryDto from(PostInfo postInfo) {
			String userName = postInfo
					.getPost()
					.getIsAnonymous() ? "익명" : postInfo.getPost().getUser().getUsername();
			return PostSummaryDto.builder()
					.postId(postInfo.getPost().getId())
					.title(postInfo.getPost().getTitle())
					.content(postInfo.getPost().getContent())
					.userName(userName)
					.createdAt(postInfo.getPost().getCreatedAt())
					.isAnonymous(postInfo.getPost().getIsAnonymous())
					.likesCount(postInfo.getPostLikesList().size())
					.commentCount(postInfo.getComments().size())
					.hashtag(postInfo.getPost().getHashtag().getName())
					.build();
		}
	}
}
