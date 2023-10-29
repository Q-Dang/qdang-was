package com.qdang.application.user.port.out;

public interface CheckRefreshTokenPort {

	void checkValidRefreshToken(Long userId, String refreshToken);
}
