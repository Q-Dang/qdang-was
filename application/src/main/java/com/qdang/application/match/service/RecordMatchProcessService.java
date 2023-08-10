package com.qdang.application.match.service;

import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.command.RecordMatchProcessCommand;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RecordMatchProcessService implements RecordMatchProcessUseCase {



	@Override
	public void recordMatchProcess(RecordMatchProcessCommand command) {
		//match process 저장
		//user match process 저장
	}
}
