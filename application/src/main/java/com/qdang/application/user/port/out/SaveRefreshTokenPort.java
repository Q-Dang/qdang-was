package com.qdang.application.user.port.out;

import java.util.concurrent.TimeUnit;

public interface SaveRefreshTokenPort {

	void save(Long userId, String refreshToken, Integer ttl, TimeUnit unit);
}
