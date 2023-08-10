package com.qdang.adapter.user;

import com.qdang.persistence.user.UserJpaEntity;
import java.util.Optional;

public interface UserRepository {

	UserJpaEntity save(UserJpaEntity user);

	Optional<UserJpaEntity> findById(Long id);

	Optional<UserJpaEntity> findByUsername(String username);

	Optional<UserJpaEntity> findByLoginId(String loginId);
}
