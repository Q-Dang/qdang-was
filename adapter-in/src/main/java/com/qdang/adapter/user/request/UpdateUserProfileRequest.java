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
public class UpdateUserProfileRequest {

	@Schema(description = "닉네임")
	private String username;

	@Schema(description = "생년월일", example = "2000-01-01")
	private LocalDate birthday;

	@Schema(description = "성별")
	private Gender gender;

	@Schema(description = "1~5숙련도")
	@Range(min = 1, max = 5)
	private Integer proficiency;

	public UpdateUserProfileCommand toUpdateUserProfileCommand(Long userId ) {
		return UpdateUserProfileCommand.of(userId, username, birthday, gender, proficiency);
	}
}
