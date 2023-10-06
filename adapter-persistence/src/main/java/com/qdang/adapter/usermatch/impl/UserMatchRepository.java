package com.qdang.adapter.usermatch.impl;

import com.qdang.adapter.usermatch.custom.UserMatchRepositoryCustom;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface UserMatchRepository extends
		Repository<UserMatchJpaEntity, Long>,
		UserMatchRepositoryCustom {

	UserMatchJpaEntity save(UserMatchJpaEntity userMatchJpaEntity);

	List<UserMatchJpaEntity> saveAll(Iterable<UserMatchJpaEntity> userMatchJpaEntity);

	List<UserMatchJpaEntity> findAllByUserId(Long userId);

	List<UserMatchJpaEntity> findAllByMatchId(Long matchId);
}
