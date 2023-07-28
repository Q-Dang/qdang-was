package com.qdang.application.match.service;

import com.qdang.application.match.port.in.StartMatchUseCase;
import com.qdang.application.match.port.in.command.StartMatchCommand;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.application.match.domain.Match;
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
