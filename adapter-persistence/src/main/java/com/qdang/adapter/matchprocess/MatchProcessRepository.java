package com.qdang.adapter.matchprocess;

import com.qdang.adapter.matchprocess.custom.MatchProcessRepositoryCustom;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import org.springframework.data.repository.Repository;

public interface MatchProcessRepository extends
		Repository<MatchProcessJpaEntity, Long>,
		MatchProcessRepositoryCustom {

}
