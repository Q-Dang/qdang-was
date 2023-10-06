package com.qdang.adapter.usermatchprocess.impl;

import com.qdang.adapter.usermatchprocess.custom.UserMatchProcessRepositoryCustom;
import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserMatchProcessRepository extends
		Repository<UserMatchProcessJpaEntity, Long>,
		UserMatchProcessRepositoryCustom {

	Optional<UserMatchProcessJpaEntity> findByUserIdAndMatchProcessId(Long userId, Long matchProcessId);

	UserMatchProcessJpaEntity save(UserMatchProcessJpaEntity userMatchProcessJpaEntity);

	List<UserMatchProcessJpaEntity> saveAll(Iterable<UserMatchProcessJpaEntity> userMatchProcessJpaEntities);
}
