package qdang.group.was.user.service;

import qdang.group.was.user.service.dto.request.LoginInfo;
import qdang.group.was.user.service.dto.request.SignUpInfo;
import qdang.group.was.user.service.dto.response.JwtToken;

public interface AuthService {

	void signUp(SignUpInfo request);

	JwtToken login(LoginInfo request);

	boolean isPresentUserName(String username);

	boolean isWrongPassword(String rawPassword, String encodedPassword);
}
