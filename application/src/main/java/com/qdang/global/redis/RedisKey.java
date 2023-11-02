package com.qdang.global.redis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisKey {

	REFRESH_TOKEN("qdang::refreshToken::%s"),
	ANNOUNCEMENT("qdang::noticeBoard::%s"),
	;

	private final String key;

	public String of(String arg) {
		return String.format(key, arg);
	}
}
