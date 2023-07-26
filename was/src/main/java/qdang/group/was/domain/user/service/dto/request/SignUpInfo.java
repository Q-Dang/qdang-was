package qdang.group.was.domain.user.service.dto.request;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import qdang.group.was.domain.user.domain.User;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpInfo {

	private String userId;
	private String password;
	private String username;
	private LocalDate birthday;
	private String gender;
	private int proficiency;

	public void passwordEncoded(PasswordEncoder passwordEncoder) {
		password = passwordEncoder.encode(password);
	}

	public static SignUpInfo of(String userId, String password, String username, LocalDate birthday,
		String gender, int proficiency) {
		return SignUpInfo.builder()
			.userId(userId)
			.password(password)
			.username(username)
			.birthday(birthday)
			.gender(gender)
			.proficiency(proficiency)
			.build();
	}

	public User newUser() {
		return User.of(userId, password, username, birthday, gender, proficiency);
	}
}
