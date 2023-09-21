package com.qdang.application.match.port.in;

import com.qdang.application.match.Vo.MatchDetail;

public interface GetMatchUseCase {

	MatchDetail getMatchDetailInfoByMatchId(Long matchId);
}
