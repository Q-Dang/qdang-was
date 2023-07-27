package com.qdang.api.user.service;

import com.qdang.api.user.exception.ConflictUserNameException;
import com.qdang.api.user.exception.InvalidPasswordException;
import com.qdang.api.user.exception.NotFoundUserException;
import com.qdang.api.user.service.dto.request.LoginInfo;
import com.qdang.api.user.service.dto.request.SignUpInfo;
import com.qdang.api.user.service.dto.response.TokenCollection;
import com.qdang.core.user.User;
import com.qdang.global.jwt.JwtService;
import com.qdang.global.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.qdang.api.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;


	@Override
	public void signUp(SignUpInfo request) {
		if (isPresentUserId(request.getUserId())) {
			throw new ConflictUserNameException();
		}
		request.passwordEncoded(passwordEncoder);
		userRepository.save(request.newUser());
	}

	@Override
	public TokenCollection login(LoginInfo request) {
		User user = userRepository.findByUserId(request.getUserId())
			.orElseThrow(NotFoundUserException::new);
		if (isWrongPassword(request.getPassword(), user.getPassword())) {
			throw new InvalidPasswordException();
		}
		return generateTokenCollection(TokenInfo.from(user));
	}

	@Override
	public TokenCollection generateTokenCollection(TokenInfo tokenInfo) {
		String accessToken = jwtService.createAccessToken(tokenInfo);
		String refreshToken = jwtService.createRefreshToken();
		return TokenCollection.of(accessToken, refreshToken);
	}

	@Override
	public boolean isPresentUserId(String userId) {
		return userRepository.findByUserId(userId)
			.isPresent();
	}

	@Override
	public boolean isWrongPassword(String rawPassword, String encodedPassword) {
		return !passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
