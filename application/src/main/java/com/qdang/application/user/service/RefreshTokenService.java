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
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
class RefreshTokenService implements RefreshTokenUseCase {

	private final LoadUserPort loadUserPort;
	private final SaveUserPort saveUserPort;
	private final JwtResolver jwtResolver;
	private final JwtProvider jwtProvider;

	@Override
	@Transactional
	public TokenCollection refreshToken(String refreshToken) {
		jwtResolver.validateRefreshToken(refreshToken);
		Long userId = jwtResolver.getUserIdFromRefreshToken(refreshToken);
		User user = loadUserPort.loadById(userId);
		user.validateLongin();
		user.validateRefreshToken(refreshToken);
		TokenCollection tokenCollection =
				jwtProvider.createTokenCollection(TokenInfo.from(user));
		user.updateRefreshToken(tokenCollection.getRefreshToken());
		saveUserPort.save(user);
		return tokenCollection;
	}
}
