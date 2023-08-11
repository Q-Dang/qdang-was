package com.qdang.adapter.matchprocess;

import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import java.util.Optional;

public interface MatchProcessRepository {

	Optional<MatchProcessJpaEntity> findById(Long id);

	MatchProcessJpaEntity save(MatchProcessJpaEntity matchProcessJpaEntity);
}
