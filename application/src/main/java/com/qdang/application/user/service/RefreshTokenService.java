package com.qdang.application.user.service;

import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.RefreshTokenUseCase;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.global.exception.InvalidException;
import com.qdang.global.jwt.JwtProvider;
import com.qdang.global.jwt.JwtResolver;
import com.qdang.global.jwt.TokenInfo;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
class RefreshTokenService implements RefreshTokenUseCase {

	private final LoadUserPort loadUserPort;
	private final SaveUserPort saveUserPort;
	private final JwtResolver jwtResolver;
	private final JwtProvider jwtProvider;

	@Override
	public TokenCollection refreshToken(String accessToken, String refreshToken) {
		jwtResolver.validateAccessToken(accessToken);
		jwtResolver.validateRefreshToken(refreshToken);
		Long userId = jwtResolver.getUserIdFromAccessToken(accessToken);
		User user = loadUserPort.loadById(userId);
		if (user.isNotLoggedIn()) {
			throw new InvalidException("로그인이 안 된 유저입니다.");
		}
		if (!user.validateRefreshToken(refreshToken)) {
			throw new InvalidException("올바르지 않은 리프레시 토큰입니다.");
		}
		TokenCollection tokenCollection =
				jwtProvider.createTokenCollection(TokenInfo.from(user));
		user.updateRefreshToken(tokenCollection.getRefreshToken());
		saveUserPort.save(user);
		return tokenCollection;
	}
}
