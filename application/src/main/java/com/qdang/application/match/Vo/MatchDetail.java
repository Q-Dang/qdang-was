package com.qdang.application.match.Vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchDetailInfo {

	/**
	 * match
	 *
	 * - 경기 내 유저 기록 List ( UserMatchDetailInfo )
	 * 		- user Match
	 * 		- user
	 * 		- 한 카운트당 유저 기록 List
	 * 			- match process
	 * 			- user match process
	 */

}
