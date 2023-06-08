package qdang.group.was.user.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class LoginInfo {

	private String username;
	private String password;

	public static LoginInfo of(String username, String password) {
		return LoginInfo.builder()
			.username(username)
			.password(password)
			.build();
	}
}
