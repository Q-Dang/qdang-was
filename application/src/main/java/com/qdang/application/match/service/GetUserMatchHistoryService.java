package com.qdang.application.match.service;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchHistory;
import com.qdang.application.match.port.in.GetUserMatchHistoryUseCase;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserMatchHistoryService implements GetUserMatchHistoryUseCase {

	private final LoadUserMatchPort loadUserMatchPort;
	private final LoadMatchPort loadMatchPort;

	// Todo : User Match 와 Match 를 짝지어서 리턴하는 함수 만들기
	@Override
	public List<MatchHistory> getMatchHistoryByUserId(Long userId) {
		List<UserMatch> userMatches = loadUserMatchPort.loadAllByUserId(userId);
		List<MatchHistory> matchHistories = userMatches.stream()
			.map(userMatch -> {
				Match match = loadMatchPort.loadById(userMatch.getMatchId());
				return MatchHistory.of(match, userMatch);
			})
			.collect(Collectors.toList());
		return matchHistories;
	}
}