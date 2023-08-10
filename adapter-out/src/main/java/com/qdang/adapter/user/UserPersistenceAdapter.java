package com.qdang.adapter.user;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.application.user.port.out.CheckUserPort;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements
	LoadUserPort,
	CheckUserPort,
	SaveUserPort {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public boolean hasUserByUsername(String username) {
		return userRepository.findByUsername(username)
			.isPresent();
	}

	@Override
	public boolean hasUserByLoginId(String loginId) {
		System.out.println("UserPersistenceAdapter.hasUserByLoginId");
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
	public User save(User user) {
		System.out.println("UserPersistenceAdapter.save");
		UserJpaEntity userJpaEntity =
			userMapper.mapToJpaEntity(user);
		System.out.println("UserPersistenceAdapter.save");
		userRepository.save(userJpaEntity);
		System.out.println("UserPersistenceAdapter.save");
		return userMapper.mapToDomainEntity(userJpaEntity);
	}
}
