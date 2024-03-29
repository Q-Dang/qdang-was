package com.qdang.application.match.service;

import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import com.qdang.application.match.port.in.StartMatchUseCase;
import com.qdang.application.match.port.in.command.StartMatchCommand;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.UserMatch;
import com.qdang.application.match.port.out.SaveUserMatchPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class StartMatchService implements StartMatchUseCase {

	private final LoadUserPort loadUserPort;
	private final SaveMatchPort saveMatchPort;
	private final SaveUserMatchPort saveUserMatchPort;

	@Override
	@Transactional
	public Match startMatch(StartMatchCommand command) {
		Match match =
				saveMatchPort.save(
						Match.newMatch(
								command.getMatchType(),
								command.getUserCount()));
		List<UserMatch> userMatches = command
				.getMatchTargetScoreList()
				.stream()
				.map(matchTargetScore ->
						UserMatch.newUserMatch(
								loadUserPort.loadById(matchTargetScore.getUserId()),
								match,
								matchTargetScore.getTargetScore(),
								matchTargetScore.getCushionTargetScore(),
								matchTargetScore.getBankShotTargetScore()))
				.collect(Collectors.toList());
		saveUserMatchPort.saveAll(userMatches);
		return match;
	}
}
