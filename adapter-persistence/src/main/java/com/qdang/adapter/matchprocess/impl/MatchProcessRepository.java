package com.qdang.adapter.matchprocess.impl;

import com.qdang.adapter.matchprocess.custom.MatchProcessRepositoryCustom;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MatchProcessRepository extends
		Repository<MatchProcessJpaEntity, Long>,
		MatchProcessRepositoryCustom {

	Optional<MatchProcessJpaEntity> findById(Long id);

	MatchProcessJpaEntity save(MatchProcessJpaEntity matchProcessJpaEntity);
}
