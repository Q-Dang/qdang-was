package com.qdang.api.user.adapter.out;

import com.qdang.api.user.adapter.out.persistence.UserRepositoryImpl;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.application.user.port.out.CheckUserPort;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements
	LoadUserPort,
	CheckUserPort,
	SaveUserPort {

	private final UserRepositoryImpl userRepository;
	private final UserMapper userMapper;

	@Override
	public boolean hasUserByUsername(String username) {
		return userRepository.findByUsername(username)
			.isPresent();
	}

	@Override
	public boolean hasUserByLoginId(String loginId) {
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
		UserJpaEntity userJpaEntity =
			userMapper.mapToJpaEntity(user);
		userRepository.save(userJpaEntity);
		return userMapper.mapToDomainEntity(userJpaEntity);
	}
}
