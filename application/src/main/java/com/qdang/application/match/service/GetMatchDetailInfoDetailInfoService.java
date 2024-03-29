package com.qdang.application.match.service;

import com.qdang.application.match.domain.vo.MatchDetail;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.in.GetMatchDetailInfoUseCase;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.match.port.out.LoadMatchProcessPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.match.domain.UserMatch;
import com.qdang.application.match.port.out.LoadUserMatchPort;
import com.qdang.application.match.domain.UserMatchProcess;
import com.qdang.application.match.port.out.LoadUserMatchProcessPort;
import com.qdang.global.usecase.UseCase;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Slf4j
class GetMatchDetailInfoDetailInfoService implements GetMatchDetailInfoUseCase {

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
		return getMatchDetail(
				match,
				userMatches);
	}

	private MatchDetail getMatchDetail(Match match, List<UserMatch> userMatches) {
		List<MatchDetail.UserMatchHistory> userMatchHistories =
				getUserMatchHistories(
						match.getId(),
						userMatches);
		return MatchDetail.of(
				match,
				userMatchHistories);
	}

	private List<MatchDetail.UserMatchHistory> getUserMatchHistories(Long matchId, List<UserMatch> userMatches) {
		return userMatches
				.stream()
				.map(userMatch -> {
					User user = loadUserPort.loadById(userMatch.getUser().getId());
					List<MatchProcess> matchProcesses =
							loadMatchProcessPort.loadAllByMatchIdAscPhaseCountDescProcessCount(matchId);

					List<MatchProcess> filterMatchProcesses = filterMatchProcessIsMaxProcessCount(
							matchProcesses);

					List<MatchDetail.UserMatchHistory.UserMatchProcessHistory> userMatchProcessHistories =
							getUserMatchProcessHistories(
									user,
									filterMatchProcesses);
					return MatchDetail.UserMatchHistory.of(
							user,
							userMatch,
							userMatchProcessHistories);
				})
				.collect(Collectors.toList());
	}

	private List<MatchProcess> filterMatchProcessIsMaxProcessCount(List<MatchProcess> matchProcesses) {
		List<MatchProcess> filterMatchProcesses = new ArrayList<>();
		if (matchProcesses.size() == 0) {
			return filterMatchProcesses;
		}
		filterMatchProcesses.add(matchProcesses.get(0));
		for (int i = 1; i < matchProcesses.size(); i++) {
			if (!matchProcesses.get(i).getPhaseCount().equals(matchProcesses.get(i - 1).getPhaseCount())) {
				filterMatchProcesses.add(matchProcesses.get(i));
			} else {
				if (matchProcesses.get(i).getProcessCount() > matchProcesses.get(i - 1).getProcessCount()) {
					filterMatchProcesses.remove(matchProcesses.get(i - 1));
					filterMatchProcesses.add(matchProcesses.get(i));
				}
			}
		}
		return filterMatchProcesses;
	}

	private List<MatchDetail.UserMatchHistory.UserMatchProcessHistory> getUserMatchProcessHistories(
			User user,
			List<MatchProcess> matchProcesses) {
		List<MatchDetail.UserMatchHistory.UserMatchProcessHistory> userMatchProcessHistories =
				matchProcesses
						.stream()
						.map(matchProcess -> {
							UserMatchProcess userMatchProcess =
									loadUserMatchProcessPort.loadByUserIdAndMatchProcessId(
											user.getId(),
											matchProcess.getId());
							return MatchDetail.UserMatchHistory.UserMatchProcessHistory.of(
									matchProcess,
									userMatchProcess);
						})
						.collect(Collectors.toList());
		// userMatchProcessHistories 에서 중복 phaseCount 를 제거하고,
		return userMatchProcessHistories;
	}
}
