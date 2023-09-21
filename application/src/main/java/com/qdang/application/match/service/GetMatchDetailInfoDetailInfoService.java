package com.qdang.application.match.service;

import com.qdang.application.match.Vo.MatchDetail;
import com.qdang.application.match.Vo.UserMatchDetail;
import com.qdang.application.match.Vo.UserMatchProcessHistory;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.in.GetMatchUseCase;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.matchprocess.port.out.LoadMatchProcessPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import com.qdang.application.usermatchprocess.port.out.LoadUserMatchProcessPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Slf4j
class GetMatchDetailInfoService implements GetMatchUseCase {

	private final LoadMatchPort loadMatchPort;
	private final LoadUserPort loadUserPort;
	private final LoadUserMatchPort loadUserMatchPort;
	private final LoadMatchProcessPort loadMatchProcessPort;
	private final LoadUserMatchProcessPort loadUserMatchProcessPort;

	@Override
	@Transactional(readOnly = true)
	public MatchDetail getMatchDetailInfoByMatchId(Long matchId) {
		Match match = loadMatchPort.loadById(matchId);
		List<UserMatch> userMatches = loadUserMatchPort.loadAllByMatchId(matchId);
		List<UserMatchDetail> userMatchDetails =
				getUserMatchDetails(
						matchId,
						userMatches);
		return MatchDetail.of(
				match,
				userMatchDetails);
	}

	private List<UserMatchDetail> getUserMatchDetails(Long matchId, List<UserMatch> userMatches) {
		return userMatches
				.stream()
				.map(userMatch -> {
					User user = loadUserPort.loadById(userMatch.getUserId());
					List<MatchProcess> matchProcesses =
							loadMatchProcessPort.loadAllByMatchId(matchId);
					List<UserMatchProcessHistory> userMatchProcessHistories =
							getUserMatchProcessHistories(
									user,
									matchProcesses);
					return UserMatchDetail.of(
							user,
							userMatch,
							userMatchProcessHistories);
				})
				.collect(Collectors.toList());
	}

	private List<UserMatchProcessHistory> getUserMatchProcessHistories(
			User user,
			List<MatchProcess> matchProcesses) {
		return matchProcesses
				.stream()
				.map(matchProcess -> {
					UserMatchProcess userMatchProcess =
							loadUserMatchProcessPort.loadByUserIdAndMatchProcessId(
									user.getId(),
									matchProcess.getId());
					return UserMatchProcessHistory.of(
							matchProcess,
							userMatchProcess);
				})
				.collect(Collectors.toList());
	}
}
