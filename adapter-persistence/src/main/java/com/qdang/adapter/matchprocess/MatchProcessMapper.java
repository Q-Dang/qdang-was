package com.qdang.adapter.matchprocess;

import com.qdang.adapter.match.MatchMapper;
import com.qdang.adapter.match.impl.MatchRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.exception.NotFoundMatchException;
import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.user.domain.User;
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
	private final MatchMapper matchMapper;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public MatchProcess mapToDomainEntity(MatchProcessJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		MatchProcess.MatchProcessBuilder matchProcess = MatchProcess.builder();
		matchProcess.id(jpaEntity.getId());
		matchProcess.match(getMatch(jpaEntity));
		matchProcess.player(getPlayer(jpaEntity));
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
		MatchProcessJpaEntity.MatchProcessJpaEntityBuilder matchProcessJpaEntity = MatchProcessJpaEntity.builder();
		matchProcessJpaEntity.id(domain.getId());
		matchProcessJpaEntity.match(getMatchJpaEntity(domain));
		matchProcessJpaEntity.player(getPlayerJpaEntity(domain));
		matchProcessJpaEntity.duration(domain.getDuration());
		matchProcessJpaEntity.processCount(domain.getProcessCount());
		matchProcessJpaEntity.turnCount(domain.getTurnCount());
		matchProcessJpaEntity.phaseCount(domain.getPhaseCount());
		matchProcessJpaEntity.isValid(domain.getIsValid());
		return matchProcessJpaEntity.build();
	}

	private Match getMatch(MatchProcessJpaEntity jpaEntity) {
		if (jpaEntity.getMatch().getClass() == MatchJpaEntity.class) {
			return matchMapper.mapToDomainEntity(jpaEntity.getMatch());
		}
		return Match.init(jpaEntity.getMatch().getId());
	}

	private User getPlayer(MatchProcessJpaEntity jpaEntity) {
		if (jpaEntity.getPlayer().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getPlayer());
		}
		return User.init(jpaEntity.getPlayer().getId());
	}

	private MatchJpaEntity getMatchJpaEntity(MatchProcess domain) {
		if (domain.getMatch() == null) {
			return null;
		}
		return matchRepository.findById(domain.getMatch().getId())
						.orElseThrow(NotFoundMatchException::new);
	}

	private UserJpaEntity getPlayerJpaEntity(MatchProcess domain) {
		if (domain.getPlayer() == null) {
			return null;
		}
		return userRepository.findById(domain.getPlayer().getId())
						.orElseThrow(NotFoundUserException::new);
	}
}
