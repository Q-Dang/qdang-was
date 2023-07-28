package com.qdang.api.match.adapter.out.persistence;

import com.qdang.persistence.match.MatchJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepositoryImpl extends JpaRepository<MatchJpaEntity, Long> {
}
