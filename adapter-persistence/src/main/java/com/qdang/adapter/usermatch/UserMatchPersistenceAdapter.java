package com.qdang.adapter.usermatch;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import com.qdang.application.usermatch.port.out.SaveUserMatchPort;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

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
		userMatch = userMatchMapper.mapToDomainEntity(userMatchJpaEntity);
		return userMatch;
	}

	@Override
	public List<UserMatch> loadAllByUserId(Long userId) {
		List<UserMatchJpaEntity> userMatchJpaEntities =
			userMatchRepository.findAllByUserId(userId);
		return userMatchJpaEntities.stream()
			.map(userMatchMapper::mapToDomainEntity)
			.collect(Collectors.toList());
	}
}
