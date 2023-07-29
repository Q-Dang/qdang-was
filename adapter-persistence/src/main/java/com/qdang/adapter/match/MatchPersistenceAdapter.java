package com.qdang.adapter.match;

import com.qdang.application.match.exception.NotFoundMatchException;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.persistence.match.MatchJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class MatchPersistenceAdapter implements
	LoadMatchPort,
	SaveMatchPort {

	private final MatchRepository matchRepository;
	private final MatchMapper matchMapper;

	@Override
	public Match save(Match match) {
		MatchJpaEntity matchJpaEntity = matchMapper.mapToJpaEntity(match);
		matchRepository.save(matchJpaEntity);
		match = matchMapper.mapToDomainEntity(matchJpaEntity);
		return match;
	}

	@Override
	public Match loadById(Long matchId) {
		MatchJpaEntity matchJpaEntity =
				matchRepository.findById(matchId)
				.orElseThrow(NotFoundMatchException::new);
		return matchMapper.mapToDomainEntity(matchJpaEntity);
	}
}
