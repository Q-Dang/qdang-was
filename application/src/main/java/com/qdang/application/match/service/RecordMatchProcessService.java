package com.qdang.application.match.service;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.exception.InvalidPlayerIdException;
import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.matchprocess.port.out.SaveMatchProcessPort;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import com.qdang.application.usermatchprocess.port.out.SaveUserMatchProcessPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class RecordMatchProcessService implements RecordMatchProcessUseCase {

	private final SaveMatchProcessPort saveMatchProcessPort;
	private final SaveUserMatchProcessPort saveUserMatchProcessPort;
	private final LoadMatchPort loadMatchPort;
	private final LoadUserMatchPort loadUserMatchPort;

	@Override
	@Transactional
	public void recordMatchProcess(RecordMatchProcessCommand command) {
		validateCommand(command);
		MatchProcess matchProcess = saveMatchProcessPort.save(command.getMatchProcess());
		List<UserMatchProcess> userMatchProcessList = command.getUserMatchProcessList();
		userMatchProcessList
				.forEach(userMatchProcess -> {
					userMatchProcess.setMatchProcessId(matchProcess.getId());
				});
		saveUserMatchProcessPort.saveAll(command.getUserMatchProcessList());
	}

	private void validateCommand(RecordMatchProcessCommand command) {
		MatchProcess matchProcess = command.getMatchProcess();
		List<UserMatchProcess> userMatchProcessList = command.getUserMatchProcessList();
		validateMatchId(matchProcess.getMatchId());
		validateUserMatchProcessListByMatchId(matchProcess.getMatchId(), userMatchProcessList);
		validatePlayerId(matchProcess.getPlayerId(), userMatchProcessList);
	}

	private void validateMatchId(Long matchId) {
		Match match = loadMatchPort.loadById(matchId);
	}

	private void validateUserMatchProcessListByMatchId(Long matchId, List<UserMatchProcess> userMatchProcessList) {
		List<UserMatch> userMatchList = loadUserMatchPort.loadAllByMatchId(matchId);
		List<Long> userMatchProcessListUserIds = userMatchProcessList
				.stream()
				.map(UserMatchProcess::getUserId)
				.collect(Collectors.toList());
		List<Long> userMatchListUserId = userMatchList.stream()
				.map(UserMatch::getUserId)
				.collect(Collectors.toList());
		if (!userMatchListUserId.containsAll(userMatchProcessListUserIds)) {
			throw new InvalidPlayerIdException();
		}
	}

	private void validatePlayerId(Long playerId, List<UserMatchProcess> userMatchProcessList) {
		List<Long> userMatchProcessListUserIds = userMatchProcessList
				.stream()
				.map(UserMatchProcess::getUserId)
				.collect(Collectors.toList());
		if (!userMatchProcessListUserIds.contains(playerId)) {
			throw new InvalidPlayerIdException();
		}
	}
}
