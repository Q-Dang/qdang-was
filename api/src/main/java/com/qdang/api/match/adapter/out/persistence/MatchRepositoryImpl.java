package com.qdang.api.match.adapter.out.persistence;

import com.qdang.core.match.MatchJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepositoryImpl extends JpaRepository<MatchJpaEntity, Long> {
}
