package com.qdang.api.match.adapter.out;

import com.qdang.application.match.domain.Match;
import com.qdang.application.match.domain.MatchType;
import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.match.MatchTypeJpa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-28T16:30:52+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Override
    public MatchJpaEntity mapToJpaEntity(Match domain) {
        if ( domain == null ) {
            return null;
        }

        MatchJpaEntity.MatchJpaEntityBuilder matchJpaEntity = MatchJpaEntity.builder();

        matchJpaEntity.matchTypeCode( matchTypeToMatchTypeJpa( domain.getMatchType() ) );
        matchJpaEntity.matchTypeName( matchTypeToMatchTypeJpa( domain.getMatchType() ) );
        matchJpaEntity.id( domain.getId() );
        matchJpaEntity.createdAt( domain.getCreatedAt() );
        matchJpaEntity.updatedAt( domain.getUpdatedAt() );
        matchJpaEntity.isDeleted( domain.getIsDeleted() );
        matchJpaEntity.isValid( domain.getIsValid() );
        matchJpaEntity.endAt( domain.getEndAt() );
        matchJpaEntity.duration( domain.getDuration() );
        matchJpaEntity.userCount( domain.getUserCount() );

        return matchJpaEntity.build();
    }

    @Override
    public Match mapToDomainEntity(MatchJpaEntity jpaEntity) {
        if ( jpaEntity == null ) {
            return null;
        }

        Match.MatchBuilder match = Match.builder();

        match.id( jpaEntity.getId() );
        match.createdAt( jpaEntity.getCreatedAt() );
        match.updatedAt( jpaEntity.getUpdatedAt() );
        match.isDeleted( jpaEntity.getIsDeleted() );
        match.isValid( jpaEntity.getIsValid() );
        match.endAt( jpaEntity.getEndAt() );
        match.duration( jpaEntity.getDuration() );
        match.userCount( jpaEntity.getUserCount() );

        return match.build();
    }

    protected MatchTypeJpa matchTypeToMatchTypeJpa(MatchType matchType) {
        if ( matchType == null ) {
            return null;
        }

        MatchTypeJpa matchTypeJpa;

        switch ( matchType ) {
            case THREE_BALL: matchTypeJpa = MatchTypeJpa.THREE_BALL;
            break;
            case FOUR_BALL: matchTypeJpa = MatchTypeJpa.FOUR_BALL;
            break;
            case POCKET_BALL: matchTypeJpa = MatchTypeJpa.POCKET_BALL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + matchType );
        }

        return matchTypeJpa;
    }
}
