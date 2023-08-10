package com.qdang.application.usermatchprocess.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchProcess {

	private Long id;
	private Long userId;
	private Long matchProcessId;
	private Integer score;
	private Integer finishCushionScore;
	private Integer finishBankShotScore;
	private Integer ranking;
	private UserMatchStatus status;
	private Integer maxHighRun;
	private Integer highRun;
	private Integer deltaScore;
	private String turnType;
	private Boolean isMyTurn;
	private Integer inningCount;
	private Integer succeedInningCount;
	private Integer failedInningCount;
}
