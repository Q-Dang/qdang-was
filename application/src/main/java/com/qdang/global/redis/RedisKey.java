package com.qdang.global.redis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisKey {

	REFRESH_TOKEN("user:%d:refreshToken")
	;

	private final String key;

	public String of(Object arg) {
		return String.format(key, arg);
	}
}
