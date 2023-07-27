package com.qdang.api.match.out;

import com.qdang.core.match.Match;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepositoryImpl extends JpaRepository<Match, Long> {

	Optional<Match> findById(Long Long);
}
