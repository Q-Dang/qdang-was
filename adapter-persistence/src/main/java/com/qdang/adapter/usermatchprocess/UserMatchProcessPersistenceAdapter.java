package com.qdang.adapter.usermatchprocess;

import com.qdang.adapter.usermatchprocess.impl.UserMatchProcessRepository;
import com.qdang.application.match.port.out.LoadUserMatchProcessPort;
import com.qdang.global.exception.NotFoundException;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.match.domain.UserMatchProcess;
import com.qdang.application.match.port.out.SaveUserMatchProcessPort;
import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
class UserMatchProcessPersistenceAdapter implements
		LoadUserMatchProcessPort,
		SaveUserMatchProcessPort {

	private final UserMatchProcessRepository userMatchProcessRepository;
	private final UserMatchProcessMapper userMatchProcessMapper;

	@Override
	public UserMatchProcess loadByUserIdAndMatchProcessId(Long userId, Long matchProcessId) {
		return userMatchProcessMapper
				.mapToDomainEntity(
				userMatchProcessRepository
						.findByUserIdAndMatchProcessId(
								userId,
								matchProcessId)
						.orElseThrow(NotFoundException::new)
		);
	}

	@Override
	public UserMatchProcess save(UserMatchProcess userMatchProcess) {
		UserMatchProcessJpaEntity userMatchProcessJpaEntity =
				userMatchProcessMapper.mapToJpaEntity(userMatchProcess);
		userMatchProcessRepository.save(userMatchProcessJpaEntity);
		userMatchProcess = userMatchProcessMapper.mapToDomainEntity(userMatchProcessJpaEntity);
		return userMatchProcess;
	}

	@Override
	public List<UserMatchProcess> saveAll(List<UserMatchProcess> userMatchProcessList) {
		List<UserMatchProcessJpaEntity> userMatchProcessJpaEntities = userMatchProcessList
				.stream()
				.map(userMatchProcessMapper::mapToJpaEntity)
				.collect(Collectors.toList());
		userMatchProcessRepository.saveAll(userMatchProcessJpaEntities);
		return userMatchProcessJpaEntities
				.stream()
				.map(userMatchProcessMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}
}
