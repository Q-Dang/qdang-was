package com.qdang.adapter.match.persistence;

import com.qdang.persistence.match.MatchJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepositoryImpl extends JpaRepository<MatchJpaEntity, Long> {
}
