package com.qdang.adapter.match;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.out.LoadMatchPort;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.persistence.match.MatchJpaEntity;
import lombok.RequiredArgsConstructor;

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
		return matchMapper.mapToDomainEntity(matchJpaEntity);
	}

	@Override
	public Match loadById(Long matchId) {
		MatchJpaEntity matchJpaEntity = matchRepository.findById(matchId).orElseThrow();
		return matchMapper.mapToDomainEntity(matchJpaEntity);
	}
}
