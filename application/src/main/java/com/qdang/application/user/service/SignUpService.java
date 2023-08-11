package com.qdang.application.user.service;

import com.qdang.global.jwt.JwtProvider;
import com.qdang.global.jwt.TokenInfo;
import com.qdang.global.usecase.UseCase;
import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.domain.UserRole;
import com.qdang.application.user.exception.ConflictUserNameException;
import com.qdang.application.user.port.in.SignUpUseCase;
import com.qdang.application.user.port.in.command.SignUpCommand;
import com.qdang.application.user.port.out.CheckUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.application.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final SaveUserPort saveUserPort;
	private final CheckUserPort checkUserPort;

	@Override
	@Transactional
	public TokenCollection signUp(SignUpCommand command) {
		if (checkUserPort.hasUserByLoginId(command.getLoginId())) {
			throw new ConflictUserNameException();
		}
		User user = User.of(command.getLoginId(), command.getPassword(), UserRole.MEMBER);
		user.encodePassword(passwordEncoder);
		User saveUser = saveUserPort.save(user);
		return generateTokenCollection(TokenInfo.from(saveUser));
	}

	public TokenCollection generateTokenCollection(TokenInfo tokenInfo) {
		String accessToken = jwtProvider.createAccessToken(tokenInfo);
		String refreshToken = jwtProvider.createRefreshToken();
		return TokenCollection.of(accessToken, refreshToken);
	}
}
