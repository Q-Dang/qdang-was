package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Comment;

public interface SaveCommentPort {

	Comment save(Comment comment);
}
