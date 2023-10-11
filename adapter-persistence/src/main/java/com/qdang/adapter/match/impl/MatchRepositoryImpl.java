package com.qdang.adapter.match.impl;

import static com.qdang.persistence.match.QMatchJpaEntity.*;

import com.qdang.adapter.match.custom.MatchRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepositoryCustom {

	private final JPAQueryFactory queryFactory;
}
