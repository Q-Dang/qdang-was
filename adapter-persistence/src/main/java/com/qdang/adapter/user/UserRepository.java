package com.qdang.adapter.user;


import com.qdang.adapter.user.custom.UserRepositoryCustom;
import com.qdang.persistence.user.UserJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserRepository extends
		Repository<UserJpaEntity, Long>,
		UserRepositoryCustom {

	UserJpaEntity save(UserJpaEntity user);

	List<UserJpaEntity> saveAll(Iterable<UserJpaEntity> user);

	Optional<UserJpaEntity> findById(Long id);

	Optional<UserJpaEntity> findByUsername(String username);

	Optional<UserJpaEntity> findByLoginId(String loginId);
}
