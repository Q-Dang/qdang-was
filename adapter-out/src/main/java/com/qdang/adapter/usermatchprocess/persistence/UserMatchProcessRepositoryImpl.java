package com.qdang.adapter.usermatchprocess.persistence;

import com.qdang.adapter.usermatchprocess.UserMatchProcessRepository;
import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import org.springframework.data.repository.Repository;

public interface UserMatchProcessRepositoryImpl extends
		Repository<UserMatchProcessJpaEntity, Long>,
		UserMatchProcessRepository {

}
