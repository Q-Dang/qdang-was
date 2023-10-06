package com.qdang.application.match.service;

import com.qdang.global.usecase.UseCase;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.vo.MatchHistory;
import com.qdang.application.match.port.in.GetUserMatchHistoryUseCase;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.domain.UserMatch;
import com.qdang.application.match.port.out.LoadUserMatchPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class GetUserMatchHistoryService implements GetUserMatchHistoryUseCase {

	private final LoadUserMatchPort loadUserMatchPort;
	private final LoadMatchPort loadMatchPort;

	@Override
	@Transactional(readOnly = true)
	public List<MatchHistory> getMatchHistoryByUserId(Long userId) {
		List<UserMatch> userMatches = loadUserMatchPort.loadAllByUserId(userId);
		List<MatchHistory> matchHistories = userMatches
				.stream()
				.map(userMatch -> {
					Match match = loadMatchPort.loadById(userMatch.getMatchId());
					return MatchHistory.of(match, userMatch);
				})
				.collect(Collectors.toList());
		return matchHistories;
	}
}
