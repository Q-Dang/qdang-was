package com.qdang.adapter.matchprocess.persistence;

import com.qdang.adapter.matchprocess.MatchProcessRepository;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import org.springframework.data.repository.Repository;

public interface MatchProcessRepositoryImpl extends
		Repository<MatchProcessJpaEntity, Long>,
		MatchProcessRepository {

}
