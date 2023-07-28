package com.qdang.api.match.adapter.out;

import com.qdang.api.match.domain.Match;
import com.qdang.core.match.MatchJpaEntity;
import com.qdang.global.mapper.GenericJpaMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchMapper extends GenericJpaMapper<Match, MatchJpaEntity> {

}
