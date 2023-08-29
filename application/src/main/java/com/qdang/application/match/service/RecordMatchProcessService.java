package com.qdang.application.match.service;

import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.matchprocess.port.out.SaveMatchProcessPort;
import com.qdang.application.usermatchprocess.port.out.SaveUserMatchProcessPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class RecordMatchProcessService implements RecordMatchProcessUseCase {

	private final SaveMatchProcessPort saveMatchProcessPort;
	private final SaveUserMatchProcessPort saveUserMatchProcessPort;

	@Override
	@Transactional
	public void recordMatchProcess(RecordMatchProcessCommand command) {
		MatchProcess matchProcess = saveMatchProcessPort.save(command.getMatchProcess());
		command.getUserMatchProcessList()
				.forEach(userMatchProcess -> {
					userMatchProcess.setMatchProcessId(matchProcess.getId());
				});
		saveUserMatchProcessPort.saveAll(command.getUserMatchProcessList());
	}
}
