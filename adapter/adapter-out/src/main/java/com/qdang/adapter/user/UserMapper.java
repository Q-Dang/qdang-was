package com.qdang.adapter.user;

import com.qdang.application.user.domain.User;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.user.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericJpaMapper<User, UserJpaEntity> {

	@Override
	@Mapping(target = "userRole",
		expression = "java(UserRole.getUserRole(userJpaEntity.getUserRole()))")
	User mapToDomainEntity(UserJpaEntity userJpaEntity);

	@Override
	@Mapping(target = "userRole", source = "user.userRole.name")
	UserJpaEntity mapToJpaEntity(User user);
}
