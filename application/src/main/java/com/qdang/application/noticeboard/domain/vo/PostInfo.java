package com.qdang.application.noticeboard.domain.vo;

import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.PostLikes;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostInfo {

	private Post post;
	private List<PostLikes> postLikesList;
	private List<Comment> comments;

	public static PostInfo of(
			Post post,
			List<PostLikes> postLikesList,
			List<Comment> comments) {
		return new PostInfo(
				post,
				postLikesList,
				comments);
	}
}
