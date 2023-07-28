package com.qdang.api.user.adapter.out;

import com.qdang.persistence.user.UserJpaEntity;
import java.util.Optional;

public interface UserRepository {

	UserJpaEntity save(UserJpaEntity user);
	Optional<UserJpaEntity> findByUsername(String username);
	Optional<UserJpaEntity> findByLoginId(String loginId);
}
