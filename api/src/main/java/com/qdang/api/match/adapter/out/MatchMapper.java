package com.qdang.api.match.adapter.out;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchType;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.match.MatchJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatchMapper extends GenericJpaMapper<Match, MatchJpaEntity> {

	@Override
	@Mapping(target = "matchType",
		expression = "java(MatchType.getMatchType(matchJpaEntity.getMatchTypeCode()))")
	Match mapToDomainEntity(MatchJpaEntity matchJpaEntity);

	@Override
	@Mapping(target = "matchTypeCode", source = "match.matchType.code")
	@Mapping(target = "matchTypeName", source = "match.matchType.name")
	MatchJpaEntity mapToJpaEntity(Match match);
}
