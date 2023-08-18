package com.qdang.application.match.service;

import com.qdang.global.usecase.UseCase;
import com.qdang.application.match.port.in.StartMatchUseCase;
import com.qdang.application.match.port.in.command.StartMatchCommand;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.application.match.domain.Match;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.SaveUserMatchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class StartMatchService implements StartMatchUseCase {

	private final SaveMatchPort saveMatchPort;
	private final SaveUserMatchPort saveUserMatchPort;


	// Todo : save
	@Override
	@Transactional
	public Match startMatch(StartMatchCommand command) {
		Match match = Match.of(command.getMatchType(), command.getUserCount());
		Match saveMatch = saveMatchPort.save(match);
		command.getMatchTargetScoreList().stream()
			.map(matchTargetScore -> UserMatch.of(
				matchTargetScore.getUserId(),
				saveMatch.getId(),
				matchTargetScore.getTargetScore(),
				matchTargetScore.getCushionTargetScore(),
				matchTargetScore.getBankShotTargetScore()))
			.forEach(saveUserMatchPort::save);
		return saveMatch;
	}
}
