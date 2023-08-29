package com.qdang.application.match.port.in;

import com.qdang.application.match.domain.Match;

public interface GetMatchUseCase {

	Match getMatchByMatchId(Long matchId);
}
