package com.qdang.application.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MatchType {

	THREE_BALL,
	FOUR_BALL,
	POCKET_BALL;
}