package com.qdang.application.match.service;

import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import com.qdang.application.matchprocess.port.out.SaveMatchProcessPort;
import com.qdang.application.usermatchprocess.port.out.SaveUserMatchProcessPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RecordMatchProcessService implements RecordMatchProcessUseCase {

	private final SaveMatchProcessPort saveMatchProcessPort;
	private final SaveUserMatchProcessPort saveUserMatchProcessPort;

	@Override
	@Transactional
	public void recordMatchProcess(RecordMatchProcessCommand command) {
		saveMatchProcessPort.save(command.getMatchProcess());
		saveUserMatchProcessPort.saveAll(command.getUserMatchProcessList());
	}
}
