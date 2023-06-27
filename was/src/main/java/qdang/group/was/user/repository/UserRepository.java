package qdang.group.was.user.repository;

import java.util.Optional;
import qdang.group.was.match.domain.Match;
import qdang.group.was.user.domain.User;

public interface UserRepository {

	void save(User user);

	Optional<User> findByUsername(String username);
}
