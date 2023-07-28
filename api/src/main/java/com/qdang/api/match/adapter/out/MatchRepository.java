package com.qdang.api.match.adapter.out;

import com.qdang.core.match.MatchJpaEntity;
import java.util.Optional;

public interface MatchRepository {

	Optional<MatchJpaEntity> findById(Long Long);
}
