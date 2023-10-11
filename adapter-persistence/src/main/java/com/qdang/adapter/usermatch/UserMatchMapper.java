package com.qdang.adapter.usermatch;

import com.qdang.adapter.match.MatchMapper;
import com.qdang.adapter.match.impl.MatchRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.exception.NotFoundMatchException;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.application.match.domain.UserMatch;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class UserMatchMapper implements
		GenericJpaMapper<UserMatch, UserMatchJpaEntity> {

	private final MatchRepository matchRepository;
	private final MatchMapper matchMapper;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserMatch mapToDomainEntity(UserMatchJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}

		return UserMatch.builder()
				.id(jpaEntity.getId())
				.user(getUser(jpaEntity))
				.match(getMatch(jpaEntity))
				.targetScore(jpaEntity.getTargetScore())
				.finishCushionTargetScore(jpaEntity.getFinishCushionTargetScore())
				.finishBankShotTargetScore(jpaEntity.getFinishBankShotTargetScore())
				.createdAt(jpaEntity.getCreatedAt())
				.updatedAt(jpaEntity.getUpdatedAt())
				.isDeleted(jpaEntity.getIsDeleted())
				.score(jpaEntity.getScore())
				.finishCushionScore(jpaEntity.getFinishCushionScore())
				.finishBankShotScore(jpaEntity.getFinishBankShotScore())
				.ranking(jpaEntity.getRanking())
				.maxHighRun(jpaEntity.getMaxHighRun())
				.average(jpaEntity.getAverage())
				.inningCount(jpaEntity.getInningCount())
				.succeedInningCount(jpaEntity.getSucceedInningCount())
				.failedInningCount(jpaEntity.getFailedInningCount())
				.sluggingCount(jpaEntity.getSluggingCount())
				.build();
	}

	@Override
	public UserMatchJpaEntity mapToJpaEntity(UserMatch domain) {
		if (domain == null) {
			return null;
		}
		return UserMatchJpaEntity.builder()
				.id(domain.getId())
				.user(getUserJpaEntity(domain))
				.match(getMatchJpaEntity(domain))
				.targetScore(domain.getTargetScore())
				.finishCushionTargetScore(domain.getFinishCushionTargetScore())
				.finishBankShotTargetScore(domain.getFinishBankShotTargetScore())
				.createdAt(domain.getCreatedAt())
				.updatedAt(domain.getUpdatedAt())
				.isDeleted(domain.getIsDeleted())
				.score(domain.getScore())
				.finishCushionScore(domain.getFinishCushionScore())
				.finishBankShotScore(domain.getFinishBankShotScore())
				.ranking(domain.getRanking())
				.maxHighRun(domain.getMaxHighRun())
				.average(domain.getAverage())
				.inningCount(domain.getInningCount())
				.succeedInningCount(domain.getSucceedInningCount())
				.failedInningCount(domain.getFailedInningCount())
				.sluggingCount(domain.getSluggingCount())
				.build();
	}

	private Match getMatch(UserMatchJpaEntity jpaEntity) {
		if (jpaEntity.getMatch().getClass() == MatchJpaEntity.class) {
			return matchMapper.mapToDomainEntity(jpaEntity.getMatch());
		}
		return Match.init(jpaEntity.getMatch().getId());
	}

	private User getUser(UserMatchJpaEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private MatchJpaEntity getMatchJpaEntity(UserMatch domain) {
		if (domain.getMatch() == null) {
			return null;
		}
		return matchRepository.findById(domain.getMatch().getId())
				.orElseThrow(NotFoundMatchException::new);
	}

	private UserJpaEntity getUserJpaEntity(UserMatch domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}
}
