package qdang.group.was.user.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtToken {

	private String jwtToken;

	public static JwtToken of(String jwtToken) {
		return new JwtToken(jwtToken);
	}
}
