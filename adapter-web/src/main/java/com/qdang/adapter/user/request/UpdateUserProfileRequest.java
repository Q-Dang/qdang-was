package com.qdang.adapter.user.request;

import com.qdang.application.user.domain.Gender;
import com.qdang.application.user.port.in.command.UpdateUserProfileCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 프로필 수정 요청")
public class UpdateUserProfileRequest {

	@Schema(description = "닉네임")
	private String username;

	@Schema(description = "생년월일", example = "2000-01-01")
	private LocalDate birthday;

	@Schema(description = "성별", example = "MALE, FEMALE, OTHER")
	private Gender gender;

	@Schema(description = "1~5숙련도")
	@Range(min = 1, max = 5, message = "{range.user.proficiency}")
	private Integer proficiency;

	public UpdateUserProfileCommand toUpdateUserProfileCommand(Long userId) {
		return UpdateUserProfileCommand.of(userId, username, birthday, gender, proficiency);
	}
}
