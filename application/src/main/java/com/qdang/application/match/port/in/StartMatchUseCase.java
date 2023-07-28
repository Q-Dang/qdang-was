package com.qdang.application.match.port.in;


import com.qdang.application.match.port.in.command.StartMatchCommand;

public interface StartMatchUseCase {

	void startMatch(StartMatchCommand command);

}
