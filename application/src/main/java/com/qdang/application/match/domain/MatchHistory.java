package com.qdang.application.match.domain;

import com.qdang.application.usermatch.domain.UserMatch;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchHistory {

	private Long matchId;
	private Integer userCount;
	private MatchType matchType;
	private LocalDateTime createdAt;
	private Integer score;
	private Integer finishCushionScore;
	private Integer finishBankShotScore;
	private Integer maxHighRun;
	private Boolean isDeleted;
	private Boolean isValid;
	private Integer ranking;

	public static MatchHistory of(Match match, UserMatch userMatch) {
		return MatchHistory.builder()
			.matchId(match.getId())
			.userCount(match.getUserCount())
			.matchType(match.getMatchType())
			.createdAt(match.getCreatedAt())
			.score(userMatch.getScore())
			.finishCushionScore(userMatch.getFinishCushionScore())
			.finishBankShotScore(userMatch.getFinishBankShotScore())
			.maxHighRun(userMatch.getMaxHighRun())
			.isDeleted(match.getIsDeleted())
			.isValid(match.getIsValid())
			.ranking(userMatch.getRanking())
			.build();
	}

}
