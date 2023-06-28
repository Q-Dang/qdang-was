package qdang.group.was.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qdang.group.was.global.jwt.JwtService;
import qdang.group.was.global.jwt.TokenInfo;
import qdang.group.was.user.domain.User;
import qdang.group.was.user.exception.ConflictUserNameException;
import qdang.group.was.user.exception.InvalidPasswordException;
import qdang.group.was.user.exception.NotFoundUserException;
import qdang.group.was.user.repository.UserRepository;
import qdang.group.was.user.service.dto.request.LoginInfo;
import qdang.group.was.user.service.dto.request.SignUpInfo;
import qdang.group.was.user.service.dto.response.TokenCollection;

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
