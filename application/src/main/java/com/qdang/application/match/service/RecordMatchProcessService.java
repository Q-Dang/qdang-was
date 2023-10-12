package com.qdang.application.match.service;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.match.port.out.SaveMatchProcessPort;
import com.qdang.application.match.domain.UserMatchProcess;
import com.qdang.application.match.port.out.SaveUserMatchProcessPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class RecordMatchProcessService implements RecordMatchProcessUseCase {

	private final LoadMatchPort loadMatchPort;
	private final LoadUserPort loadUserPort;
	private final SaveMatchProcessPort saveMatchProcessPort;
	private final SaveUserMatchProcessPort saveUserMatchProcessPort;

	@Override
	@Transactional
	public void recordMatchProcess(RecordMatchProcessCommand command) {
		Match match = loadMatchPort.loadById(command.getMatchId());
		User Player = loadUserPort.loadById(command.getPlayerId());
		MatchProcess matchProcess = saveMatchProcessPort.save(
				MatchProcess.newInstance(
						match,
						Player,
						command.getDuration(),
						command.getProcessCount(),
						command.getTurnCount(),
						command.getPhaseCount(),
						command.getIsValid()));
		List<UserMatchProcess> userMatchProcesses = command.getUserMatchProcessCommandList()
				.stream()
				.map(userMatchProcessCommand -> {
					return UserMatchProcess.newUserMatchProcess(
							loadUserPort.loadById(userMatchProcessCommand.getUserId()),
							matchProcess,
							userMatchProcessCommand.getScore(),
							userMatchProcessCommand.getFinishCushionScore(),
							userMatchProcessCommand.getFinishBankShotScore(),
							userMatchProcessCommand.getRanking(),
							userMatchProcessCommand.getStatus(),
							userMatchProcessCommand.getMaxHighRun(),
							userMatchProcessCommand.getHighRun(),
							userMatchProcessCommand.getDeltaScore(),
							userMatchProcessCommand.getTurnType(),
							userMatchProcessCommand.getIsMyTurn(),
							userMatchProcessCommand.getInningCount(),
							userMatchProcessCommand.getSucceedInningCount(),
							userMatchProcessCommand.getFailedInningCount());
				})
				.collect(Collectors.toList());
		saveUserMatchProcessPort.saveAll(userMatchProcesses);
	}
}
