package com.qdang.application.match.port.in;


import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.in.command.StartMatchCommand;

public interface StartMatchUseCase {

	Match startMatch(StartMatchCommand command);
}