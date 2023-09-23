package com.qdang.application.match.service;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.exception.InvalidPlayerIdException;
import com.qdang.application.match.port.in.QuitGameUseCase;
import com.qdang.application.match.port.in.command.QuitMatchCommand;
import com.qdang.application.match.port.in.command.UserMatchResultCommand;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import com.qdang.application.usermatch.port.out.SaveUserMatchPort;
import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.ForbiddenException;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import java.util.stream.Collectors;
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
				loadUserMatchPort.loadAllByMatchId(command.getMatchId());
		List<User> users =
				loadUserPort.loadAllByMatchId(match.getId());
		checkValidationPlayerId(command.getPlayerId(), userMatches);
		checkValidationPlayerIds(getUserIdsInCommand(command), userMatches);
		if (match.isQuitMatch()) {
			throw new BusinessException(ErrorType.INVALID_INPUT_EXCEPTION, "이미 종료된 게임입니다.");
		}
		match.quit(command.getPlayTime());
		command.getUserMatchResultCommand().forEach(userMatchResultCommand -> {
			UserMatch userMatch =
					getUserMatch(userMatchResultCommand.getUserId(), userMatches);
			userMatch.quit(
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
			User user =
					getUser(userMatch.getUserId(), users);
			user.applyUserMatchRecord(userMatch);
		});
		saveMatchPort.save(match);
		saveUserMatchPort.saveAll(userMatches);
		saveUserPort.saveAll(users);
	}

	private static User getUser(Long userId, List<User> users) {
		return users
				.stream()
				.filter(it -> it.getId().equals(userId))
				.findFirst()
				.orElseThrow(InvalidPlayerIdException::new);
	}

	private static List<Long> getUserIdsInCommand(QuitMatchCommand command) {
		return command
				.getUserMatchResultCommand()
				.stream()
				.map(UserMatchResultCommand::getUserId)
				.collect(Collectors.toList());
	}

	private UserMatch getUserMatch(Long userId, List<UserMatch> userMatches) {
		return userMatches
				.stream()
				.filter(it -> it.getUserId().equals(userId))
				.findFirst()
				.orElseThrow(InvalidPlayerIdException::new);
	}

	private void checkValidationPlayerId(Long playerId, List<UserMatch> userMatches) {
		List<Long> userIds = userMatches
				.stream()
				.map(UserMatch::getUserId)
				.collect(Collectors.toList());
		if (!userIds.contains(playerId)) {
			throw new ForbiddenException();
		}
	}

	private void checkValidationPlayerIds(List<Long> playerIds, List<UserMatch> userMatches) {
		List<Long> userMatchUserIds = userMatches
				.stream()
				.map(UserMatch::getUserId)
				.collect(Collectors.toList());
		if (!userMatchUserIds.containsAll(playerIds)) {
			throw new InvalidPlayerIdException();
		}
	}
}
