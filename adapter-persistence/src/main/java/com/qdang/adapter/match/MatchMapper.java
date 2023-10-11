package com.qdang.adapter.match;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchType;
import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.match.MatchTypeJpa;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class MatchMapper implements GenericJpaMapper<Match, MatchJpaEntity> {

	@Override
	public Match mapToDomainEntity(MatchJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Match.MatchBuilder match = Match.builder();
		match.id(jpaEntity.getId());
		match.createdAt(jpaEntity.getCreatedAt());
		match.updatedAt(jpaEntity.getUpdatedAt());
		match.isDeleted(jpaEntity.getIsDeleted());
		match.isValid(jpaEntity.getIsValid());
		match.endAt(jpaEntity.getEndAt());
		match.duration(jpaEntity.getDuration());
		match.userCount(jpaEntity.getUserCount());
		if (jpaEntity.getMatchTypeName() != null) {
			match.matchType(matchTypeToDomain(jpaEntity.getMatchTypeName()));
		}
		return match.build();
	}

	@Override
	public MatchJpaEntity mapToJpaEntity(Match domain) {
		if (domain == null) {
			return null;
		}
		MatchJpaEntity.MatchJpaEntityBuilder matchJpaEntity = MatchJpaEntity.builder();
		matchJpaEntity.id(domain.getId());
		matchJpaEntity.createdAt(domain.getCreatedAt());
		matchJpaEntity.updatedAt(domain.getUpdatedAt());
		matchJpaEntity.isDeleted(domain.getIsDeleted());
		matchJpaEntity.isValid(domain.getIsValid());
		matchJpaEntity.endAt(domain.getEndAt());
		matchJpaEntity.duration(domain.getDuration());
		matchJpaEntity.userCount(domain.getUserCount());
		if (domain.getMatchType() != null) {
			matchJpaEntity.matchTypeCode(matchTypeToJpa(domain.getMatchType()));
			matchJpaEntity.matchTypeName(matchTypeToJpa(domain.getMatchType()));
		}
		return matchJpaEntity.build();
	}

	private MatchType matchTypeToDomain(MatchTypeJpa matchTypeJpa) {
		switch (matchTypeJpa) {
			case THREE_BALL:
				return MatchType.THREE_BALL;
			case FOUR_BALL:
				return MatchType.FOUR_BALL;
			case POCKET_BALL:
				return MatchType.POCKET_BALL;
			default:
				throw new IllegalArgumentException("Unknown match type jpa: " + matchTypeJpa);
		}
	}

	private MatchTypeJpa matchTypeToJpa(MatchType matchType) {
		switch (matchType) {
			case THREE_BALL:

				return MatchTypeJpa.THREE_BALL;
			case FOUR_BALL:
				return MatchTypeJpa.FOUR_BALL;
			case POCKET_BALL:
				return MatchTypeJpa.POCKET_BALL;
			default:
				throw new BusinessException(
						ErrorType.INVALID_INPUT_EXCEPTION,
						"Unknown match type jpa: " + matchType.name());
		}
	}
}
