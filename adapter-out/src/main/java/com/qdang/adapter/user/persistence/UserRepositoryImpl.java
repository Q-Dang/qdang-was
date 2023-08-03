package com.qdang.adapter.user.persistence;


import com.qdang.persistence.user.UserJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<UserJpaEntity, Long> {

	Optional<UserJpaEntity> findByUsername(String username);
	Optional<UserJpaEntity> findByLoginId(String loginId);
}