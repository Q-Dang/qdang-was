package qdang.group.was.domain.user.repository;

import java.util.Optional;
import qdang.group.was.domain.user.domain.User;

public interface UserRepository {

	void save(User user);

	Optional<User> findByUserId(String username);
}
