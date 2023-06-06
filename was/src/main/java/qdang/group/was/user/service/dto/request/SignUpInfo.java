package qdang.group.was.user.service.dto.request;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import qdang.group.was.user.domain.User;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpInfo {

	private String username;
	private String password;
	private LocalDate birthday;
	private int gender;
	private int proficiency;

	public void passwordEncoded(PasswordEncoder passwordEncoder) {
		password = passwordEncoder.encode(password);
	}

	public static SignUpInfo of(String username, String password, LocalDate birthday, int gender,
		int proficiency) {
		return SignUpInfo.builder()
			.username(username)
			.password(password)
			.birthday(birthday)
			.gender(gender)
			.proficiency(proficiency)
			.build();
	}

	public User newUser() {
		return User.of(username, password, birthday, gender, proficiency);
	}
}
