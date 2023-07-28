package com.qdang.api.user.adapter.out;

import com.qdang.api.user.domain.User;
import com.qdang.core.user.UserJpaEntity;
import com.qdang.global.mapper.GenericJpaMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericJpaMapper<User, UserJpaEntity> {

//	@Override
//	@Mapping(target = "joinStaffId", source = "userJpaEntity.joinStaff.id")
//	User mapToDomainEntity(UserJpaEntity userJpaEntity);
}
