package com.qdang.application.user.service;

import com.qdang.application.global.jwt.JwtProvider;
import com.qdang.application.global.jwt.TokenInfo;
import com.qdang.application.global.usecase.UseCase;
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
	public TokenCollection signUp(SignUpCommand request) {
		System.out.println("SignUpService.signUp");
		if (checkUserPort.hasUserByLoginId(request.getLoginId())) {
			throw new ConflictUserNameException();
		}
		System.out.println("SignUpService.signUp");
		User user = User.of(request.getLoginId(), request.getPassword(), UserRole.MEMBER);
		user.encodePassword(passwordEncoder);
		System.out.println("SignUpService.signUp");
		saveUserPort.save(user);
		return generateTokenCollection(TokenInfo.from(user));
	}

	public TokenCollection generateTokenCollection(TokenInfo tokenInfo) {
		String accessToken = jwtProvider.createAccessToken(tokenInfo);
		String refreshToken = jwtProvider.createRefreshToken();
		return TokenCollection.of(accessToken, refreshToken);
	}
}
