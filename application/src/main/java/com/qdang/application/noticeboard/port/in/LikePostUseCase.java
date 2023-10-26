package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.port.in.command.LikePostCommand;

public interface LikePostUseCase {

	void likePost(LikePostCommand command);
}
