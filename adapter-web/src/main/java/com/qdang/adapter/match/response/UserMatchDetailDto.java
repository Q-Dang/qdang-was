package com.qdang.adapter.match.response;

import com.qdang.application.match.Vo.UserMatchDetail;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMatchDetailDto {

	private Long userId;
	private String profileImage;
	private String username;
	private Integer rank;
	private Integer score;
	private Integer sluggingPercentage;
	private List<UserMatchProcessHistoryDto> userMatchProcessHistoryList;

	public static UserMatchDetailDto from(UserMatchDetail userMatchDetail) {
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
		return 100 * userMatchDetail.getUserMatch().getSluggingCount() / userMatchDetail.getUserMatch().getInningCount();
	}

}
