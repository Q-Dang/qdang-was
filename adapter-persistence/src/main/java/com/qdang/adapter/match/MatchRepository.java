package com.qdang.adapter.match;


import com.qdang.persistence.match.MatchJpaEntity;
import java.util.Optional;

public interface MatchRepository {

	MatchJpaEntity save(MatchJpaEntity match);

//	Optional<MatchJpaEntity> getMatchId(Long matchId);

	Optional<MatchJpaEntity> findById(Long Long);
}
