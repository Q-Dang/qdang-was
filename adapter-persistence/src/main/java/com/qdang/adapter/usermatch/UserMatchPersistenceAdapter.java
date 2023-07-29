package com.qdang.adapter.usermatch;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import com.qdang.application.usermatch.port.out.SaveUserMatchPort;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserMatchPersistenceAdapter implements
		LoadUserMatchPort,
		SaveUserMatchPort {

	private final UserMatchRepository userMatchRepository;
	private final UserMatchMapper userMatchMapper;

	@Override
	public UserMatch save(UserMatch userMatch) {
		UserMatchJpaEntity userMatchJpaEntity =
				userMatchMapper.mapToJpaEntity(userMatch);
		userMatchRepository.save(userMatchJpaEntity);
		return userMatchMapper.mapToDomainEntity(userMatchJpaEntity);
	}

	@Override
	public List<UserMatch> saveAll(List<UserMatch> userMatches) {
		List<UserMatchJpaEntity> userMatchJpaEntities = userMatches
				.stream()
				.map(userMatchMapper::mapToJpaEntity)
				.collect(Collectors.toList());
		userMatchRepository.saveAll(userMatchJpaEntities);
		return userMatchJpaEntities
				.stream()
				.map(userMatchMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<UserMatch> loadAllByUserId(Long userId) {
		return userMatchRepository
				.findAllByUserId(userId)
				.stream()
				.map(userMatchMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<UserMatch> loadAllByMatchId(Long matchId) {
		return userMatchRepository
				.findAllByMatchId(matchId)
				.stream()
				.map(userMatchMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}
}
