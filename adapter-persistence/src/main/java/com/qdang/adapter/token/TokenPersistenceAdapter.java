package com.qdang.adapter.token;

import com.qdang.application.user.port.out.CheckRefreshTokenPort;
import com.qdang.application.user.port.out.DeleteRefreshTokenPort;
import com.qdang.application.user.port.out.SaveRefreshTokenPort;
import com.qdang.global.exception.InvalidException;
import com.qdang.global.redis.RedisKey;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class TokenPersistenceAdapter implements
		SaveRefreshTokenPort,
		CheckRefreshTokenPort,
		DeleteRefreshTokenPort {

	private final StringRedisTemplate stringRedisTemplate;

	@Override
	public void save(Long userId, String refreshToken, Integer ttl, TimeUnit unit) {
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set(
				RedisKey.REFRESH_TOKEN.of(userId),
				refreshToken);
	}

	@Override
	public void checkValidRefreshToken(Long userId, String refreshToken) {
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		String savedRefreshToken =
				valueOperations.get(RedisKey.REFRESH_TOKEN.of(userId));
		if (savedRefreshToken == null) {
			throw new InvalidException("로그인이 안 된 유저입니다.");
		}
		if (!refreshToken.equals(savedRefreshToken)) {
			throw new InvalidException("올바르지 않은 리프레시 토큰입니다.");
		}
	}

	@Override
	public void delete(Long userId) {
		stringRedisTemplate.delete(RedisKey.REFRESH_TOKEN.of(userId));
	}
}
