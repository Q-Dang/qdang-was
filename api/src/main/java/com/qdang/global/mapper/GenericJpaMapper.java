package com.qdang.global.mapper;

public interface GenericJpaMapper<D, P> {

	D mapToDomainEntity(P jpaEntity);
	P mapToJpaEntity(D domain);
}