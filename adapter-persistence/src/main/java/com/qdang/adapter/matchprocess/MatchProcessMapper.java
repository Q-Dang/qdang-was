package com.qdang.adapter.matchprocess;

import com.qdang.adapter.match.MatchRepository;
import com.qdang.adapter.user.UserRepository;
import com.qdang.application.match.exception.NotFoundMatchException;
import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class MatchProcessMapper implements
		GenericJpaMapper<MatchProcess, MatchProcessJpaEntity> {

	private final MatchRepository matchRepository;
	private final UserRepository userRepository;

	@Override
	public MatchProcess mapToDomainEntity(MatchProcessJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		MatchProcess.MatchProcessBuilder matchProcess = MatchProcess.builder();
		matchProcess.id(jpaEntity.getId());
		matchProcess.matchId(jpaEntity.getMatch().getId());
		matchProcess.playerId(jpaEntity.getPlayer().getId());
		matchProcess.duration(jpaEntity.getDuration());
		matchProcess.processCount(jpaEntity.getProcessCount());
		matchProcess.turnCount(jpaEntity.getTurnCount());
		matchProcess.phaseCount(jpaEntity.getPhaseCount());
		matchProcess.isValid(jpaEntity.getIsValid());
		return matchProcess.build();
	}

	@Override
	public MatchProcessJpaEntity mapToJpaEntity(MatchProcess domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity =
				userRepository.findById(domain.getPlayerId())
						.orElseThrow(NotFoundUserException::new);
		MatchJpaEntity matchJpaEntity =
				matchRepository.findById(domain.getMatchId())
						.orElseThrow(NotFoundMatchException::new);
		MatchProcessJpaEntity.MatchProcessJpaEntityBuilder matchProcessJpaEntity = MatchProcessJpaEntity.builder();
		matchProcessJpaEntity.id(domain.getId());
		matchProcessJpaEntity.match(matchJpaEntity);
		matchProcessJpaEntity.player(userJpaEntity);
		matchProcessJpaEntity.duration(domain.getDuration());
		matchProcessJpaEntity.processCount(domain.getProcessCount());
		matchProcessJpaEntity.turnCount(domain.getTurnCount());
		matchProcessJpaEntity.phaseCount(domain.getPhaseCount());
		matchProcessJpaEntity.isValid(domain.getIsValid());
		return matchProcessJpaEntity.build();
	}
}
