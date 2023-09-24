package com.qdang.adapter.matchprocess;

import com.qdang.adapter.matchprocess.custom.MatchProcessRepositoryCustom;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MatchProcessRepository extends
		Repository<MatchProcessJpaEntity, Long>,
		MatchProcessRepositoryCustom {

	Optional<MatchProcessJpaEntity> findById(Long id);

	List<MatchProcessJpaEntity> findAllByMatchId(Long matchId);

	MatchProcessJpaEntity save(MatchProcessJpaEntity matchProcessJpaEntity);
}
