package com.qdang.application.match.service;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.in.GetMatchUseCase;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Slf4j
class GetMatchService implements GetMatchUseCase {

	private final LoadMatchPort loadMatchPort;

	@Override
	@Transactional(readOnly = true)
	public Match getMatchByMatchId(Long matchId) {
		return loadMatchPort.loadById(matchId);
	}
}
