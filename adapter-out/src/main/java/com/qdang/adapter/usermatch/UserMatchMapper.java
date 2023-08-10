package com.qdang.adapter.usermatch;

import com.qdang.adapter.match.persistence.MatchRepositoryImpl;
import com.qdang.adapter.user.persistence.UserRepositoryImpl;
import com.qdang.application.match.exception.NotFoundMatchException;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMatchMapper implements GenericJpaMapper<UserMatch, UserMatchJpaEntity> {

	private final UserRepositoryImpl userRepository;
	private final MatchRepositoryImpl matchRepository;

	@Override
	public UserMatch mapToDomainEntity(UserMatchJpaEntity userMatchJpaEntity) {
		if (userMatchJpaEntity == null) {
			return null;
		}

		return UserMatch.builder()
			.id(userMatchJpaEntity.getId())
			.userId(userMatchJpaEntity.getUser().getId())
			.matchId(userMatchJpaEntity.getMatch().getId())
			.targetScore(userMatchJpaEntity.getTargetScore())
			.finishCushionTargetScore(userMatchJpaEntity.getFinishCushionTargetScore())
			.finishBankShotTargetScore(userMatchJpaEntity.getFinishBankShotTargetScore())
			.isDeleted(userMatchJpaEntity.getIsDeleted())
			.score(userMatchJpaEntity.getScore())
			.finishCushionScore(userMatchJpaEntity.getFinishCushionScore())
			.finishBankShotScore(userMatchJpaEntity.getFinishBankShotScore())
			.ranking(userMatchJpaEntity.getRanking())
			.maxHighRun(userMatchJpaEntity.getMaxHighRun())
			.average(userMatchJpaEntity.getAverage())
			.inningCount(userMatchJpaEntity.getInningCount())
			.succeedInningCount(userMatchJpaEntity.getSucceedInningCount())
			.failedInningCount(userMatchJpaEntity.getFailedInningCount())
			.build();
	}

	@Override
	public UserMatchJpaEntity mapToJpaEntity(UserMatch userMatch) {
		if (userMatch == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(userMatch.getUserId())
			.orElseThrow(NotFoundUserException::new);
		System.out.println("userMatch = " + userMatch.getMatchId());
		MatchJpaEntity matchJpaEntity = matchRepository.findById(userMatch.getMatchId())
			.orElseThrow(NotFoundMatchException::new);
		System.out.println("UserMatchMapper.mapToJpaEntity");
		return UserMatchJpaEntity.builder()
			.id(userMatch.getId())
			.user(userJpaEntity)
			.match(matchJpaEntity)
			.targetScore(userMatch.getTargetScore())
			.finishCushionTargetScore(userMatch.getFinishCushionTargetScore())
			.finishBankShotTargetScore(userMatch.getFinishBankShotTargetScore())
			.createdAt(userMatch.getCreatedAt())
			.updatedAt(userMatch.getUpdatedAt())
			.isDeleted(userMatch.getIsDeleted())
			.score(userMatch.getScore())
			.finishCushionScore(userMatch.getFinishCushionScore())
			.finishBankShotScore(userMatch.getFinishBankShotScore())
			.ranking(userMatch.getRanking())
			.maxHighRun(userMatch.getMaxHighRun())
			.average(userMatch.getAverage())
			.inningCount(userMatch.getInningCount())
			.succeedInningCount(userMatch.getSucceedInningCount())
			.failedInningCount(userMatch.getFailedInningCount())
			.build();
	}
}
