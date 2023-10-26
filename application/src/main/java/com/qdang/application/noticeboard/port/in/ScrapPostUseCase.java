package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.port.in.command.ScrapPostCommand;

public interface ScrapPostUseCase {

	void scrapPost(ScrapPostCommand	command);
}
