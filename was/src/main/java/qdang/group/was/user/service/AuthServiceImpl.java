package qdang.group.was.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qdang.group.was.global.config.jwt.JwtService;
import qdang.group.was.user.domain.User;
import qdang.group.was.user.exception.ConflictUserNameException;
import qdang.group.was.user.exception.InvalidPasswordException;
import qdang.group.was.user.exception.NotFoundUserException;
import qdang.group.was.user.repository.UserRepository;
import qdang.group.was.user.service.dto.request.LoginInfo;
import qdang.group.was.user.service.dto.request.SignUpInfo;
import qdang.group.was.user.service.dto.response.JwtToken;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;


	@Override
	public void signUp(SignUpInfo request) {
		if (isPresentUserName(request.getUsername())) {
			throw new ConflictUserNameException();
		}
		request.passwordEncoded(passwordEncoder);
		userRepository.save(request.newUser());
	}

	@Override
	public JwtToken login(LoginInfo request) {
		User user = userRepository.findByUsername(request.getUsername())
			.orElseThrow(NotFoundUserException::new);
		if (isWrongPassword(request.getPassword(), user.getPassword())) {
			throw new InvalidPasswordException();
		}
		return JwtToken.of(jwtService.issuedToken(user.getId()));
	}

	@Override
	public boolean isPresentUserName(String username) {
		return userRepository.findByUsername(username)
			.isPresent();
	}

	@Override
	public boolean isWrongPassword(String rawPassword, String encodedPassword) {
		return !passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
