package com.qdang.adapter.match.response;

import com.qdang.application.match.domain.vo.MatchDetail;
import com.qdang.application.match.domain.MatchType;
import com.qdang.application.match.domain.vo.UserMatchDetail;
import com.qdang.application.match.domain.vo.UserMatchProcessHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게임 상세 정보 조회 응답")
public class GetMatchInfoResponse {

	@Schema(description = "게임 아이디")
	private Long matchId;

	@Schema(description = "당구장 이름")
	private String billiardRoom;

	@Schema(description = "게임 타입")
	private MatchType matchType;

	@Schema(description = "게임 생성 시간")
	private LocalDateTime createdAt;

	@Schema(description = "게임 참여 인원")
	private Integer userCount;

	@Schema(description = "게임 시간")
	private LocalTime duration;

	@Schema(description = "경기 상세 정보 열럼 유저")
	private Long playerId;

	@Schema(description = "경기 내 유저 상세 정보 리스트")
	private List<UserMatchDetailDto> userMatchDetailList;

	public static GetMatchInfoResponse of(Long playerId, MatchDetail matchDetail) {
		return GetMatchInfoResponse.builder()
				.matchId(matchDetail.getMatch().getId())
				// Todo : billiardRoom 이름 가져오기
//				.billiardRoom(matchDetail.getMatch().getBilliardRoom())
				.billiardRoom("Temp Billiard Room Name")
				.matchType(matchDetail.getMatch().getMatchType())
				.createdAt(matchDetail.getMatch().getCreatedAt())
				.userCount(matchDetail.getMatch().getUserCount())
				.duration(matchDetail.getMatch().getDuration())
				.playerId(playerId)
				.userMatchDetailList(
						matchDetail
								.getUserMatchDetailList()
								.stream()
								.map(UserMatchDetailDto::from)
								.collect(Collectors.toList()))
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class UserMatchDetailDto {

		@Schema(description = "유저 아이디")
		private Long userId;

		@Schema(description = "유저 프로필 이미지")
		private String profileImage;

		@Schema(description = "유저 이름")
		private String username;

		@Schema(description = "유저 랭킹")
		private Integer rank;

		@Schema(description = "유저 점수")
		private Integer score;

		@Schema(description = "유저 장타율")
		private Integer sluggingPercentage;

		@Schema(description = "경기 내 유저 진행 기록 리스트")
		private List<UserMatchProcessHistoryDto> userMatchProcessHistoryList;

		public static UserMatchDetailDto from(
				UserMatchDetail userMatchDetail) {
			return UserMatchDetailDto.builder()
					.userId(userMatchDetail.getUser().getId())
					.profileImage(userMatchDetail.getUser().getProfileImage())
					.username(userMatchDetail.getUser().getUsername())
					.rank(userMatchDetail.getUserMatch().getRanking())
					.score(userMatchDetail.getUserMatch().getScore())
					.sluggingPercentage(getSluggingPercentage(userMatchDetail))
					.userMatchProcessHistoryList(
							userMatchDetail
									.getUserMatchProcessHistoryList()
									.stream()
									.map(UserMatchProcessHistoryDto::from)
									.collect(Collectors.toList()))
					.build();
		}

		private static int getSluggingPercentage(UserMatchDetail userMatchDetail) {
			if (userMatchDetail.getUserMatch().getInningCount() == 0) {
				return 0;
			}
			return 100 * userMatchDetail.getUserMatch().getSluggingCount() / userMatchDetail.getUserMatch().getInningCount();
		}

		@Getter
		@AllArgsConstructor(access = AccessLevel.PRIVATE)
		public static class UserMatchProcessHistoryDto {

			@Schema(description = "유저 점수")
			private Integer score;
			private Integer phaseCount;
			private Integer processCount;

			public static UserMatchProcessHistoryDto from(
					UserMatchProcessHistory userMatchProcessHistory) {
				return new UserMatchProcessHistoryDto(
						userMatchProcessHistory.getUserMatchProcess().getScore(),
						userMatchProcessHistory.getMatchProcess().getPhaseCount(),
						userMatchProcessHistory.getMatchProcess().getProcessCount()
				);
			}
		}
	}
}
