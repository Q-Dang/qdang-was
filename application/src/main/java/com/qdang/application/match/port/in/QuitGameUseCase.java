package com.qdang.application.match.port.in;

import com.qdang.application.match.port.in.command.QuitMatchCommand;

public interface QuitGameUseCase {

	void quitGame(QuitMatchCommand command);
}
