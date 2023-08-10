package com.qdang.adapter.user.persistence;


import com.qdang.adapter.user.UserRepository;
import com.qdang.persistence.user.UserJpaEntity;
import org.springframework.data.repository.Repository;

public interface UserRepositoryImpl extends
		Repository<UserJpaEntity, Long>,
		UserRepository {

}
