package com.qdang.adapter.match;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchType;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.match.MatchTypeJpa;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper implements GenericJpaMapper<Match, MatchJpaEntity> {

	@Override
	public Match mapToDomainEntity(MatchJpaEntity matchJpaEntity) {
		if (matchJpaEntity == null) {
			return null;
		}

		return Match.builder()
			.id(matchJpaEntity.getId())
			.createdAt(matchJpaEntity.getCreatedAt())
			.updatedAt(matchJpaEntity.getUpdatedAt())
			.isDeleted(matchJpaEntity.getIsDeleted())
			.isValid(matchJpaEntity.getIsValid())
			.endAt(matchJpaEntity.getEndAt())
			.duration(matchJpaEntity.getDuration())
			.userCount(matchJpaEntity.getUserCount())
			.matchType(matchTypeToDomain(matchJpaEntity.getMatchTypeName()))
			.build();
	}

	@Override
	public MatchJpaEntity mapToJpaEntity(Match match) {
		if (match == null) {
			return null;
		}

		return MatchJpaEntity.builder()
			.id(match.getId())
			.createdAt(match.getCreatedAt())
			.updatedAt(match.getUpdatedAt())
			.isDeleted(match.getIsDeleted())
			.isValid(match.getIsValid())
			.endAt(match.getEndAt())
			.duration(match.getDuration())
			.userCount(match.getUserCount())
			.matchTypeCode(matchTypeToJpa(match.getMatchType()))
			.matchTypeName(matchTypeToJpa(match.getMatchType()))
			.build();
	}

	private MatchType matchTypeToDomain(MatchTypeJpa matchTypeJpa) {
		if ( matchTypeJpa == null ) {
			return null;
		}
		return MatchType.valueOf(matchTypeJpa.name());
	}

	private MatchTypeJpa matchTypeToJpa(MatchType matchType) {
		if ( matchType == null ) {
			return null;
		}
		return MatchTypeJpa.valueOf(matchType.name());
	}

}
