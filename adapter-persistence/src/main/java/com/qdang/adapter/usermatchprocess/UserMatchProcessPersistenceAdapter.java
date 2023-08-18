package com.qdang.adapter.usermatchprocess;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import com.qdang.application.usermatchprocess.port.out.SaveUserMatchProcessPort;
import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class UserMatchProcessPersistenceAdapter implements
		SaveUserMatchProcessPort {

	private final UserMatchProcessRepository userMatchProcessRepository;
	private final UserMatchProcessMapper userMatchProcessMapper;

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
		return userMatchProcessList.stream()
				.map(this::save)
				.collect(Collectors.toList());
	}
}
