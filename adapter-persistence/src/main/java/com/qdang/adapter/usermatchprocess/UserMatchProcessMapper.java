package com.qdang.adapter.usermatchprocess;

import com.qdang.adapter.matchprocess.MatchProcessRepository;
import com.qdang.adapter.user.UserRepository;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.application.usermatchprocess.domain.TurnType;
import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import com.qdang.application.usermatchprocess.domain.UserMatchStatus;
import com.qdang.application.usermatchprocess.exception.NotFoundMatchProcessException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import com.qdang.persistence.usermatchprocess.TurnTypeJpa;
import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import com.qdang.persistence.usermatchprocess.UserMatchStatusJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Mapper
@RequiredArgsConstructor
@Slf4j
public class UserMatchProcessMapper implements
		GenericJpaMapper<UserMatchProcess, UserMatchProcessJpaEntity> {

	private final UserRepository userRepository;
	private final MatchProcessRepository matchProcessRepository;


	@Override
	public UserMatchProcess mapToDomainEntity(UserMatchProcessJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		UserMatchProcess.UserMatchProcessBuilder userMatchProcess = UserMatchProcess.builder();
		userMatchProcess.id(jpaEntity.getId());
		userMatchProcess.userId(jpaEntity.getUser().getId());
		userMatchProcess.matchProcessId(jpaEntity.getMatchProcess().getId());
		userMatchProcess.score(jpaEntity.getScore());
		userMatchProcess.finishCushionScore(jpaEntity.getFinishCushionScore());
		userMatchProcess.finishBankShotScore(jpaEntity.getFinishBankShotScore());
		userMatchProcess.ranking(jpaEntity.getRanking());
		if (jpaEntity.getStatus() != null) {
			userMatchProcess.status(
					userMatchStatusJpaToUserMatchStatus(jpaEntity.getStatus())
			);
		}
		userMatchProcess.maxHighRun(jpaEntity.getMaxHighRun());
		userMatchProcess.highRun(jpaEntity.getHighRun());
		userMatchProcess.deltaScore(jpaEntity.getDeltaScore());
		if (jpaEntity.getTurnType() != null) {
			userMatchProcess.turnType(
					turnTypeJpaToTurnType(jpaEntity.getTurnType())
			);
		}
		userMatchProcess.isMyTurn(jpaEntity.getIsMyTurn());
		userMatchProcess.inningCount(jpaEntity.getInningCount());
		userMatchProcess.succeedInningCount(jpaEntity.getSucceedInningCount());
		userMatchProcess.failedInningCount(jpaEntity.getFailedInningCount());

		return userMatchProcess.build();
	}

	@Override
	public UserMatchProcessJpaEntity mapToJpaEntity(UserMatchProcess domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity =
				userRepository.findById(domain.getUserId())
						.orElseThrow(NotFoundUserException::new);
		log.info("UserMatchProcessMapper.mapToDomainEntity");
		MatchProcessJpaEntity matchProcessJpaEntity =
				matchProcessRepository.findById(domain.getMatchProcessId())
				.orElseThrow(NotFoundMatchProcessException::new);
		log.info("UserMatchProcessMapper.mapToDomainEntity");
		UserMatchProcessJpaEntity.UserMatchProcessJpaEntityBuilder userMatchProcessJpaEntity = UserMatchProcessJpaEntity.builder();
		if(domain.getId() != null) {
			userMatchProcessJpaEntity.id(domain.getId());
		}
		log.info("UserMatchProcessMapper.mapToDomainEntity");
		userMatchProcessJpaEntity.user(userJpaEntity);
		log.info("UserMatchProcessMapper.mapToDomainEntity");
		userMatchProcessJpaEntity.matchProcess(matchProcessJpaEntity);
		userMatchProcessJpaEntity.score(domain.getScore());
		userMatchProcessJpaEntity.finishCushionScore(domain.getFinishCushionScore());
		userMatchProcessJpaEntity.finishBankShotScore(domain.getFinishBankShotScore());
		userMatchProcessJpaEntity.ranking(domain.getRanking());
		log.info("UserMatchProcessMapper.mapToJpaEntity");
		if (domain.getStatus() != null) {
			userMatchProcessJpaEntity.status(
					userMatchStatusToUserMatchStatusJpa(domain.getStatus())
			);
		}
		userMatchProcessJpaEntity.maxHighRun(domain.getMaxHighRun());
		userMatchProcessJpaEntity.highRun(domain.getHighRun());
		userMatchProcessJpaEntity.deltaScore(domain.getDeltaScore());
		if (domain.getTurnType() != null) {
			userMatchProcessJpaEntity.turnType(
					turnTypeToTurnTypeJpa(domain.getTurnType())
			);
		}
		userMatchProcessJpaEntity.isMyTurn(domain.getIsMyTurn());
		userMatchProcessJpaEntity.inningCount(domain.getInningCount());
		userMatchProcessJpaEntity.succeedInningCount(domain.getSucceedInningCount());
		userMatchProcessJpaEntity.failedInningCount(domain.getFailedInningCount());

		return userMatchProcessJpaEntity.build();
	}

	private UserMatchStatus userMatchStatusJpaToUserMatchStatus(
			UserMatchStatusJpa userMatchStatusJpa) {
		switch (userMatchStatusJpa) {
			case NORMAL:
				return UserMatchStatus.NORMAL;
			case CUSHION:
				return UserMatchStatus.CUSHION;
			case BANK_SHOT:
				return UserMatchStatus.BANK_SHOT;
			case END:
				return UserMatchStatus.END;
			default:
				throw new IllegalArgumentException(
						"Unexpected user match status jpa type: " + userMatchStatusJpa);
		}
	}

	protected UserMatchStatusJpa userMatchStatusToUserMatchStatusJpa(
			UserMatchStatus userMatchStatus) {
		switch (userMatchStatus) {
			case NORMAL:
				return UserMatchStatusJpa.NORMAL;
			case CUSHION:
				return UserMatchStatusJpa.CUSHION;
			case BANK_SHOT:
				return UserMatchStatusJpa.BANK_SHOT;
			case END:
				return UserMatchStatusJpa.END;
			default:
				throw new IllegalArgumentException(
						"Unexpected user match status type: " + userMatchStatus);
		}
	}

	TurnType turnTypeJpaToTurnType(TurnTypeJpa turnType) {
		switch (turnType) {
			case SUCCESS:
				return TurnType.SUCCESS;
			case FAIL:
				return TurnType.FAIL;
			default:
				throw new IllegalArgumentException("Unexpected turn type jpa: " + turnType);
		}
	}

	TurnTypeJpa turnTypeToTurnTypeJpa(TurnType turnType) {
		switch (turnType) {
			case SUCCESS:
				return TurnTypeJpa.SUCCESS;
			case FAIL:
				return TurnTypeJpa.FAIL;
			default:
				throw new IllegalArgumentException("Unexpected turn type: " + turnType);
		}
	}
}
