package com.qdang.api.user.service;

import com.qdang.api.user.service.dto.request.SignUpInfo;
import com.qdang.api.user.service.dto.response.TokenCollection;
import com.qdang.api.user.service.dto.request.LoginInfo;
import com.qdang.global.jwt.TokenInfo;

public interface AuthService {

	void signUp(SignUpInfo request);

	TokenCollection login(LoginInfo request);

	TokenCollection generateTokenCollection(TokenInfo tokenInfo);

	boolean isPresentUserId(String userId);

	boolean isWrongPassword(String rawPassword, String encodedPassword);
}
