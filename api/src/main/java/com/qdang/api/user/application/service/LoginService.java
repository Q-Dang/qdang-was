package com.qdang.api.user.application.service;

import com.qdang.api.user.application.port.in.LoginUseCase;
import com.qdang.api.user.application.port.in.command.LoginCommand;
import com.qdang.api.user.application.port.out.LoadUserPort;
import com.qdang.api.user.domain.TokenCollection;
import com.qdang.api.user.domain.User;
import com.qdang.global.jwt.JwtService;
import com.qdang.global.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

	private final LoadUserPort loadUserPort;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Override
	@Transactional(readOnly = true)
	public TokenCollection login(LoginCommand request) {
		User user = loadUserPort.loadByLoginId(request.getLoginId());
		user.checkPasswordByEncoder(request.getPassword(), passwordEncoder);
		// TODO: Jwt Token 부분 리펙토링하기
		return generateTokenCollection(TokenInfo.from(user));
	}

	public TokenCollection generateTokenCollection(TokenInfo tokenInfo) {
		String accessToken = jwtService.createAccessToken(tokenInfo);
		String refreshToken = jwtService.createRefreshToken();
		return TokenCollection.of(accessToken, refreshToken);
	}
}
