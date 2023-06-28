package qdang.group.was.user.service;

import qdang.group.was.global.jwt.TokenInfo;
import qdang.group.was.user.service.dto.request.LoginInfo;
import qdang.group.was.user.service.dto.request.SignUpInfo;
import qdang.group.was.user.service.dto.response.TokenCollection;

public interface AuthService {

	void signUp(SignUpInfo request);

	TokenCollection login(LoginInfo request);

	TokenCollection generateTokenCollection(TokenInfo tokenInfo);

	boolean isPresentUserId(String userId);

	boolean isWrongPassword(String rawPassword, String encodedPassword);
}
