package com.qdang.application.user.service;

import com.qdang.application.user.port.out.SaveUserPort;
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
	private final SaveUserPort saveUserPort;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Override
	@Transactional
	public TokenCollection login(LoginCommand command) {
		User user = loadUserPort.loadByLoginId(command.getLoginId());
		user.checkPasswordByEncoder(command.getPassword(), passwordEncoder);
		TokenCollection tokenCollection =
				jwtProvider.createTokenCollection(TokenInfo.from(user));
		user.updateRefreshToken(tokenCollection.getRefreshToken());
		saveUserPort.save(user);
		return tokenCollection;
	}
}
