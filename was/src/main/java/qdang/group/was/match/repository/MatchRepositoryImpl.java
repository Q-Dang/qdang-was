package qdang.group.was.match.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qdang.group.was.match.domain.Match;

public interface MatchRepositoryImpl extends JpaRepository<Match, Long> {

	Optional<Match> findById(Long aLong);
}
