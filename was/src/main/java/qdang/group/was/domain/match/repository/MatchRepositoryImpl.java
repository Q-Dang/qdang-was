package qdang.group.was.domain.match.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qdang.group.was.domain.match.domain.Match;

public interface MatchRepositoryImpl extends JpaRepository<Match, Long> {

	Optional<Match> findById(Long aLong);
}
