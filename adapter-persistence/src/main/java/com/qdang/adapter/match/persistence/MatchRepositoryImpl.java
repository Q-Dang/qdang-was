package com.qdang.adapter.match.persistence;

import com.qdang.adapter.match.MatchRepository;
import com.qdang.persistence.match.MatchJpaEntity;
import org.springframework.data.repository.Repository;

public interface MatchRepositoryImpl extends
		Repository<MatchJpaEntity, Long>,
		MatchRepository {
}
