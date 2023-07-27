package com.qdang.api.user.repository;


import com.qdang.core.user.User;
import org.springframework.data.repository.Repository;

public interface UserRepositoryImpl extends Repository<User, Long>, UserRepository {
}
