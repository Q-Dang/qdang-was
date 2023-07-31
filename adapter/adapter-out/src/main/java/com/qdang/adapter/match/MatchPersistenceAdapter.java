package com.qdang.adapter.match;

import com.qdang.adapter.match.persistence.MatchRepositoryImpl;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.out.SaveMatchPort;
import com.qdang.persistence.match.MatchJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchPersistenceAdapter implements
	SaveMatchPort {

	private final MatchRepositoryImpl matchRepository;
	private final MatchMapper matchMapper;

	@Override
	public Match save(Match match) {
		MatchJpaEntity matchJpaEntity = matchMapper.mapToJpaEntity(match);
		matchRepository.save(matchJpaEntity);
		return matchMapper.mapToDomainEntity(matchJpaEntity);
	}
}
