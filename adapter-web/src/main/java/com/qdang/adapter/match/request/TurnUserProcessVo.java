package com.qdang.adapter.match.request;

import com.qdang.application.usermatchprocess.domain.TurnType;
import com.qdang.application.usermatchprocess.domain.UserMatchStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "턴에 대한 유저 정보")
public class TurnUserProcessVo {

	@Schema(description = "유저 아이디")
	@NotNull(message = "{user.userId.notnull}")
	private Long userId;

	@Schema(description = "점수")
	@NotNull(message = "{userMatch.score.notnull}")
	private Integer score;

	@Schema(description = "쿠션 점수")
	private Integer finishCushionScore;

	@Schema(description = "뱅크샷 점수")
	private Integer finishBankShotScore;

	@Schema(description = "순위")
	@NotNull(message = "{userMatch.rank.notnull}")
	private Integer rank;

	@Schema(description = "유저 상태", example = "NORMAL, CUSHION, BANK_SHOT, END")
	@NotNull(message = "{userMatch.status.notnull}")
	private UserMatchStatus status;

	@Schema(description = "최고 장타")
	@NotNull(message = "{userMatch.maxHighRun.notnull}")
	private Integer maxHighRun;

	@Schema(description = "현재 장타")
	@NotNull(message = "{userMatch.highRun.notnull}")
	private Integer highRun;

	@Schema(description = "점수 편차")
	@NotNull(message = "{userMatch.deltaScore.notnull}")
	private Integer deltaScore;

	@Schema(description = "턴 타입", example = "SUCCESS, FAIL")
	@NotNull(message = "{userMatch.turnType.notnull}")
	private TurnType turnType;

	@Schema(description = "턴 차례 여부", defaultValue = "false")
	@NotNull(message = "{userMatch.isMyTurn.notnull}")
	private Boolean isMyTurn;

	@Schema(description = "이닝 횟수")
	@NotNull(message = "{userMatch.inningCount.notnull}")
	private Integer inningCount;

	@Schema(description = "성공 이닝 횟수")
	@NotNull(message = "{userMatch.succeedInningCount.notnull}")
	private Integer succeedInningCount;

	@Schema(description = "실패 이닝 횟수")
	@NotNull(message = "{userMatch.failedInningCount.notnull}")
	private Integer failedInningCount;
}
