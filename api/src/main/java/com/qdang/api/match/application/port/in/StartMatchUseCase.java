package com.qdang.api.match.application.port.in;

import com.qdang.api.match.application.port.in.command.StartMatchCommand;

public interface StartMatchUseCase {

	void startMatch(StartMatchCommand command);

}
