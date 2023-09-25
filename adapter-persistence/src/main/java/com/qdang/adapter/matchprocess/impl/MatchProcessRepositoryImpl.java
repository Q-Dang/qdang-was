package com.qdang.adapter.matchprocess.impl;

import static com.qdang.persistence.matchprocess.QMatchProcessJpaEntity.matchProcessJpaEntity;

import com.qdang.adapter.matchprocess.custom.MatchProcessRepositoryCustom;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchProcessRepositoryImpl implements MatchProcessRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MatchProcessJpaEntity> findAllByMatchIdAscPhaseCountDescProcessCount(Long matchId) {
		return queryFactory
				.selectFrom(matchProcessJpaEntity)
				.where(matchProcessJpaEntity.match.id.eq(matchId))
				.orderBy(matchProcessJpaEntity.phaseCount.asc(), matchProcessJpaEntity.processCount.desc())
				.fetch();
	}
}
