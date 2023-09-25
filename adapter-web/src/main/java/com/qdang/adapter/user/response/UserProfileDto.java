package com.qdang.adapter.user.response;

import com.qdang.application.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "유저 프로필")
public class UserProfileDto {

	@Schema(description = "유저 아이디", example = "1")
	private Long id;

	@Schema(description = "닉네임")
	private String username;

	@Schema(description = "프로필 이미지")
	private String profileImage;

	public static UserProfileDto from(User user) {
		return new UserProfileDto(
				user.getId(),
				user.getUsername(),
				user.getProfileImage());
	}
}
