package com.qdang.application.user.service;

import com.qdang.application.global.jwt.JwtService;
import com.qdang.application.global.jwt.TokenInfo;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final SaveUserPort saveUserPort;
	private final CheckUserPort checkUserPort;

	@Override
	@Transactional
	public TokenCollection signUp(SignUpCommand request) {
		if (checkUserPort.hasUserByLoginId(request.getLoginId())) {
			throw new ConflictUserNameException();
		}
		User user = User.of(request.getLoginId(), request.getPassword(), UserRole.MEMBER);
		user.encodePassword(passwordEncoder);
		saveUserPort.save(user);
		return generateTokenCollection(TokenInfo.from(user));
	}

	public TokenCollection generateTokenCollection(TokenInfo tokenInfo) {
		String accessToken = jwtService.createAccessToken(tokenInfo);
		String refreshToken = jwtService.createRefreshToken();
		return TokenCollection.of(accessToken, refreshToken);
	}
}
