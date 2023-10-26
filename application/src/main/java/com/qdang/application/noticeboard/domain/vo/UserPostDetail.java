package com.qdang.application.noticeboard.domain.vo;

import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.domain.Scrap;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPostDetail {

	private Post post;
	private List<PostLikes> postLikesList;
	private List<Comment> comments;
	private List<Scrap> scraps;
	private Boolean isLiked;
	private Boolean isScrapped;

	public static UserPostDetail of(
			Post post,
			List<PostLikes> postLikesList,
			List<Comment> comments,
			List<Scrap> scraps,
			Boolean isLiked,
			Boolean isScrapped) {
		return new UserPostDetail(
				post,
				postLikesList,
				comments,
				scraps,
				isLiked,
				isScrapped);
	}

}
