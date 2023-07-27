package com.qdang.api.user.repository;


import com.qdang.core.user.User;
import java.util.Optional;

public interface UserRepository {

	void save(User user);

	Optional<User> findByUserId(String username);
}
