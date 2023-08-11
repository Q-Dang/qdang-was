package com.qdang.adapter.usermatch.persistence;

import com.qdang.adapter.usermatch.UserMatchRepository;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import org.springframework.data.repository.Repository;

public interface UserMatchRepositoryImpl extends
		Repository<UserMatchJpaEntity, Long>,
		UserMatchRepository {

}
