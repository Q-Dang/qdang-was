package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.port.in.command.WritePostCommand;

public interface WritePostUseCase {

	void writePost(WritePostCommand command);
}
