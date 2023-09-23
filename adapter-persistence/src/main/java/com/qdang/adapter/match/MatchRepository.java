package com.qdang.adapter.match;

import com.qdang.adapter.match.custom.MatchRepositoryCustom;
import com.qdang.persistence.match.MatchJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MatchRepository extends
		Repository<MatchJpaEntity, Long>,
		MatchRepositoryCustom {

	MatchJpaEntity save(MatchJpaEntity match);

	Optional<MatchJpaEntity> findById(Long Long);
}
