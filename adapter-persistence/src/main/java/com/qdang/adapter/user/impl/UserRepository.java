package com.qdang.adapter.user.impl;


import com.qdang.adapter.user.custom.UserRepositoryCustom;
import com.qdang.persistence.user.UserJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends
		Repository<UserJpaEntity, Long>,
		UserRepositoryCustom {

	UserJpaEntity save(UserJpaEntity user);

	List<UserJpaEntity> saveAll(Iterable<UserJpaEntity> user);

	Optional<UserJpaEntity> findById(Long id);

	Optional<UserJpaEntity> findByUsername(String username);

	Optional<UserJpaEntity> findByLoginId(String loginId);

	@Query("select u "
			+ "from UserJpaEntity u "
			+ "where u.username like %:username%")
	List<UserJpaEntity> findAllByContainingUsername(@Param("username") String username);
}
