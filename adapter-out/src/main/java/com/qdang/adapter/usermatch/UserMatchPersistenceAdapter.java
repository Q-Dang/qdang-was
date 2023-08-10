package com.qdang.adapter.usermatch;

import com.qdang.adapter.usermatch.persistence.UserMatchRepositoryImpl;
import com.qdang.application.usermatch.domain.UserMatch;
import com.qdang.application.usermatch.port.out.LoadUserMatchPort;
import com.qdang.application.usermatch.port.out.SaveUserMatchPort;
import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMatchPersistenceAdapter implements
	LoadUserMatchPort,
	SaveUserMatchPort {

	private final UserMatchRepositoryImpl userMatchRepository;
	private final UserMatchMapper userMatchMapper;

	@Override
	public UserMatch save(UserMatch userMatch) {
		UserMatchJpaEntity userMatchJpaEntity =
			userMatchMapper.mapToJpaEntity(userMatch);
		userMatchRepository.save(userMatchJpaEntity);
		System.out.println("UserMatchPersistenceAdapter.save");
		return userMatchMapper.mapToDomainEntity(userMatchJpaEntity);
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
