package com.qdang.adapter.usermatchprocess;

import com.qdang.adapter.usermatchprocess.custom.UserMatchProcessRepositoryCustom;
import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface UserMatchProcessRepository extends
		Repository<UserMatchProcessJpaEntity, Long>,
		UserMatchProcessRepositoryCustom {

	UserMatchProcessJpaEntity save(UserMatchProcessJpaEntity userMatchProcessJpaEntity);

	List<UserMatchProcessJpaEntity> saveAll(Iterable<UserMatchProcessJpaEntity> userMatchProcessJpaEntities);
}
