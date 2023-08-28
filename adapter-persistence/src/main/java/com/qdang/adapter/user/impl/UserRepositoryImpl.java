package com.qdang.adapter.user.impl;


import static com.qdang.persistence.user.QUserJpaEntity.*;

import com.qdang.adapter.user.custom.UserRepositoryCustom;
import com.qdang.persistence.user.QUserJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<UserJpaEntity> findAllByMatchId(Long matchId) {
		return queryFactory
				.select(userJpaEntity)
				.distinct()
				.from(userJpaEntity)
				.leftJoin(userJpaEntity.userMatchList)
				.where(userJpaEntity.userMatchList.any().match.id.eq(matchId))
				.fetch();
	}
}
