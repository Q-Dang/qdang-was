package com.qdang.application.user.service;

import com.qdang.global.jwt.JwtProvider;
import com.qdang.global.jwt.TokenInfo;
import com.qdang.global.usecase.UseCase;
import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.domain.UserRole;
import com.qdang.application.user.exception.ConflictUserException;
import com.qdang.application.user.port.in.SignUpUseCase;
import com.qdang.application.user.port.in.command.SignUpCommand;
import com.qdang.application.user.port.out.CheckUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.application.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
class SignUpService implements SignUpUseCase {

	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final SaveUserPort saveUserPort;
	private final CheckUserPort checkUserPort;

	@Override
	@Transactional
	public TokenCollection signUp(SignUpCommand command) {
		if (checkUserPort.isPresentLoginId(command.getLoginId())) {
			throw new ConflictUserException();
		}
		// Todo : Random user name create refactoring
		User user =
				saveUserPort.save(
						User.of(
								command.getLoginId(),
								passwordEncoder.encode(
										command.getPassword()),
								generateRandomUsername(),
								UserRole.MEMBER));
		TokenCollection tokenCollection =
				jwtProvider.createTokenCollection(TokenInfo.from(user));
		user.updateRefreshToken(tokenCollection.getRefreshToken());
		saveUserPort.save(user);
		return tokenCollection;
	}

	private String generateRandomUsername() {
		return "user" + (int) (Math.random() * 100000);
	}
}
