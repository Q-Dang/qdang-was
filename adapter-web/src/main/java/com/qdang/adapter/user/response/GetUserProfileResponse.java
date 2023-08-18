package com.qdang.adapter.user.response;

import com.qdang.application.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "유저 프로필 조회 응답")
public class GetUserProfileResponse {

	@Schema(description = "유저 닉네임")
	private String username;

	@Schema(description = "유저 상태 메시지")
	private String statusMessage;

	@Schema(description = "유저 프로필 이미지")
	private String profileImage;

	@Schema(description = "유저 에버리지")
	private Integer average;

	@Schema(description = "유저 경기 수")
	private Integer matchCount;

	@Schema(description = "유저 최고 하이런")
	private Integer highRun;

	@Schema(description = "유저 타율")
	private Integer battingAverage;

	@Schema(description = "유저 장타율")
	private Integer sluggingPercentage;

	static public GetUserProfileResponse from(User user) {
		return new GetUserProfileResponse(
				user.getUsername(),
				user.getStatusMessage(),
				user.getProfileImage(),
				user.getAverage(),
				user.getMatchCount(),
				user.getHighRun(),
				user.getBattingAverage(),
				user.getSluggingPercentage()
		);
	}
}
