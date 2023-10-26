package com.qdang.application.noticeboard.domain;

import com.qdang.application.user.domain.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {

	private Long id;
	private NoticeBoard noticeBoard;
	private User user;
	private Hashtag hashtag;
	private Boolean isAnonymous;
	private String title;
	private String content;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static Post newPost(
			NoticeBoard noticeBoard,
			User user,
			Hashtag hashtag,
			Boolean isAnonymous,
			String title,
			String content) {
		return Post.builder()
				.noticeBoard(noticeBoard)
				.user(user)
				.hashtag(hashtag)
				.isAnonymous(isAnonymous)
				.title(title)
				.content(content)
				.isDeleted(false)
				.build();
	}

	public static Post init(Long id) {
		return Post.builder()
				.id(id)
				.build();
	}
}
