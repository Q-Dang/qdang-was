package com.qdang.application.match.service;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.exception.InvalidPlayerIdException;
import com.qdang.application.match.port.in.QuitGameUseCase;
import com.qdang.application.match.port.in.command.QuitMatchCommand;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.application.match.domain.UserMatch;
import com.qdang.application.match.port.out.LoadUserMatchPort;
import com.qdang.application.match.port.out.SaveUserMatchPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
class QuitGameService implements QuitGameUseCase {

	private final LoadMatchPort loadMatchPort;
	private final LoadUserMatchPort loadUserMatchPort;
	private final LoadUserPort loadUserPort;
	private final SaveMatchPort saveMatchPort;
	private final SaveUserMatchPort saveUserMatchPort;
	private final SaveUserPort saveUserPort;

	@Override
	@Transactional
	public void quitGame(QuitMatchCommand command) {
		Match match =
				loadMatchPort.loadById(command.getMatchId());
		List<UserMatch> userMatches =
				loadUserMatchPort.loadAllFetchUserByMatch(match);
		List<User> users =
				loadUserPort.loadAllByMatchId(match.getId());
		// player Id validation
		match.quit(command.getPlayTime());
		command.getUserMatchResultCommand()
				.forEach(userMatchResultCommand -> {
					UserMatch userMatch = getUserMatch(
							userMatchResultCommand.getUserId(),
							userMatches);
					userMatch.recordMatchResult(
							userMatchResultCommand.getScore(),
							userMatchResultCommand.getCushionScore(),
							userMatchResultCommand.getBankShotScore(),
							userMatchResultCommand.getRanking(),
							userMatchResultCommand.getMaxHighRun(),
							userMatchResultCommand.getAverage(),
							userMatchResultCommand.getInningCount(),
							userMatchResultCommand.getSucceedInningCount(),
							userMatchResultCommand.getFailedInningCount(),
							userMatchResultCommand.getSluggingCount());
					userMatch
							.getUser()
							.reflectMatchResult(userMatch);
				});
		saveMatchPort.save(match);
		saveUserMatchPort.saveAll(userMatches);
		saveUserPort.saveAll(users);
	}

	private UserMatch getUserMatch(Long userId, List<UserMatch> userMatches) {
		return userMatches
				.stream()
				.filter(it -> it.getUser().getId().equals(userId))
				.findFirst()
				.orElseThrow(InvalidPlayerIdException::new);
	}
}
