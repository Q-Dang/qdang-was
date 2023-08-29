package com.qdang.application.user.service;

import com.qdang.global.jwt.JwtProvider;
import com.qdang.global.jwt.TokenInfo;
import com.qdang.global.usecase.UseCase;
import com.qdang.application.user.port.in.LoginUseCase;
import com.qdang.application.user.port.in.command.LoginCommand;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class LoginService implements LoginUseCase {

	private final LoadUserPort loadUserPort;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Override
	@Transactional(readOnly = true)
	public TokenCollection login(LoginCommand command) {
		User user = loadUserPort.loadByLoginId(command.getLoginId());
		user.checkPasswordByEncoder(command.getPassword(), passwordEncoder);
		// TODO: Jwt Token 부분 리펙토링하기
		return generateTokenCollection(TokenInfo.from(user));
	}

	public TokenCollection generateTokenCollection(TokenInfo tokenInfo) {
		String accessToken = jwtProvider.createAccessToken(tokenInfo);
		String refreshToken = jwtProvider.createRefreshToken();
		return TokenCollection.of(accessToken, refreshToken);
	}
}
