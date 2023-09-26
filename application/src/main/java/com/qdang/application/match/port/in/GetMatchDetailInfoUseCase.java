package com.qdang.application.match.port.in;

import com.qdang.application.match.domain.vo.MatchDetail;

public interface GetMatchDetailInfoUseCase {

	MatchDetail getMatchDetailInfoByMatchId(Long matchId);
}
