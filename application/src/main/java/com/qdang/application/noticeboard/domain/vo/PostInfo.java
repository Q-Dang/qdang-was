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
public class PostSummary {

	private Post post;
	private PostLikes postLikes;
	private List<Comment> comments;
}
