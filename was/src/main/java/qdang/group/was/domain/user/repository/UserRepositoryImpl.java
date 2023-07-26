package qdang.group.was.domain.user.repository;

import org.springframework.data.repository.Repository;
import qdang.group.was.domain.user.domain.User;

public interface UserRepositoryImpl extends Repository<User, Long>, UserRepository {
}
