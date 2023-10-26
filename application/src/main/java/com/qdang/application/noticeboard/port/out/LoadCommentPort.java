package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Comment;
import java.util.List;

public interface LoadCommentPort {

	List<Comment> loadAllByPostId(Long postId);
}
