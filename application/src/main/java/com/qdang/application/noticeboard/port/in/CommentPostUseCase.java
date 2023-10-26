package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.port.in.command.CommentPostCommand;

public interface CommentPostUseCase {

	void commentPost(CommentPostCommand command);
}
