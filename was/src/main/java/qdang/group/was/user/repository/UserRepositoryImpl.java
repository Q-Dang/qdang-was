package qdang.group.was.user.repository;

import org.springframework.data.repository.Repository;
import qdang.group.was.user.domain.User;

public interface UserRepository extends Repository<User, Long>, UserRepositoryImpl {
}
