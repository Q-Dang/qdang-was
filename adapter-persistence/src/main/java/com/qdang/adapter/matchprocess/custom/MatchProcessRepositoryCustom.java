package com.qdang.adapter.matchprocess.custom;

import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import java.util.Optional;

public interface MatchProcessRepositoryCustom {

	Optional<MatchProcessJpaEntity> findById(Long id);

	MatchProcessJpaEntity save(MatchProcessJpaEntity matchProcessJpaEntity);
}
