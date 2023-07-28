package com.qdang.api.user.adapter.out.persistence;


import com.qdang.core.user.UserJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<UserJpaEntity, Long> {

	Optional<UserJpaEntity> findByUsername(String username);
	Optional<UserJpaEntity> findByLoginId(String loginId);
}
