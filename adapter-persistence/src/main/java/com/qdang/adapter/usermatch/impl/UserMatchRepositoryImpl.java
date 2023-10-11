package com.qdang.adapter.usermatch.impl;

import static com.qdang.persistence.usermatch.QUserMatchJpaEntity.*;

import com.qdang.adapter.usermatch.custom.UserMatchRepositoryCustom;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMatchRepositoryImpl implements UserMatchRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<UserMatchJpaEntity> findAllByMatchIdFetchUser(Long matchId) {
		return queryFactory
				.selectFrom(userMatchJpaEntity)
				.leftJoin(userMatchJpaEntity.user)
				.fetchJoin()
				.where(userMatchJpaEntity.match.id.eq(matchId))
				.fetch();
	}
}
