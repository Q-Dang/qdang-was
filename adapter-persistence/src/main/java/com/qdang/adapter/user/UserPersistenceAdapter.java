package com.qdang.adapter.user;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.application.user.port.out.CheckUserPort;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.persistence.user.UserJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class UserPersistenceAdapter implements
		LoadUserPort,
		CheckUserPort,
		SaveUserPort {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public boolean isPresentUsername(String username) {
		return userRepository.findByUsername(username)
				.isPresent();
	}

	@Override
	public boolean isPresentLoginId(String loginId) {
		return userRepository.findByLoginId(loginId)
				.isPresent();
	}

	@Override
	public User loadById(Long userId) {
		UserJpaEntity userJpaEntity =
				userRepository.findById(userId)
						.orElseThrow(NotFoundUserException::new);
		return userMapper.mapToDomainEntity(userJpaEntity);
	}

	@Override
	public User loadByUsername(String username) {
		UserJpaEntity userJpaEntity =
				userRepository.findByUsername(username)
						.orElseThrow(NotFoundUserException::new);
		return userMapper.mapToDomainEntity(userJpaEntity);
	}

	@Override
	public User loadByLoginId(String loginId) {
		UserJpaEntity userJpaEntity =
				userRepository.findByLoginId(loginId)
						.orElseThrow(NotFoundUserException::new);
		return userMapper.mapToDomainEntity(userJpaEntity);
	}

	@Override
	public List<User> loadAllByMatchId(Long matchId) {
		return userRepository
				.findAllByMatchId(matchId)
				.stream()
				.map(userMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<User> loadAllContainUsername(String username) {
		return userRepository
				.findAllByContainingUsername(username)
				.stream()
				.map(userMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public User save(User user) {
		UserJpaEntity userJpaEntity =
				userMapper.mapToJpaEntity(user);
		userRepository.save(userJpaEntity);
		user = userMapper.mapToDomainEntity(userJpaEntity);
		return user;
	}

	@Override
	public List<User> saveAll(List<User> users) {
		List<UserJpaEntity> userJpaEntities = users
				.stream()
				.map(userMapper::mapToJpaEntity)
				.collect(Collectors.toList());
		userJpaEntities.forEach(userJpaEntity -> {
		});

		userRepository.saveAll(userJpaEntities);
		return userJpaEntities
				.stream()
				.map(userMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}
}
