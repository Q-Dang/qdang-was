package com.qdang.api.match.application.service;

import com.qdang.api.match.application.port.in.StartMatchUseCase;
import com.qdang.api.match.application.port.in.command.StartMatchCommand;
import com.qdang.api.match.application.port.out.SaveMatchPort;
import com.qdang.api.match.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StartMatchService implements StartMatchUseCase {

	private final SaveMatchPort saveMatchPort;


	@Override
	@Transactional
	public void startMatch(StartMatchCommand command) {
		Match match = Match.of(command.getMatchType(), command.getUserCount());
//		command.getMatchTargetScoreList().stream()
//				.map()
		saveMatchPort.save(match);
	}
}
