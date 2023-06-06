package qdang.group.was.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qdang.group.was.match.repository.MatchRepositoryImpl;
import qdang.group.was.user.exception.ConflictUserNameException;
import qdang.group.was.user.repository.UserRepository;
import qdang.group.was.user.service.dto.request.LoginInfo;
import qdang.group.was.user.service.dto.request.SignUpInfo;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final MatchRepositoryImpl matchRepository;
	private final PasswordEncoder passwordEncoder;


	@Override
	public void signUp(SignUpInfo request) {
		if (isPresentUserName(request.getUsername())) {
			throw new ConflictUserNameException();
		}
		request.passwordEncoded(passwordEncoder);
		userRepository.save(request.newUser());
	}

	@Override
	public void login(LoginInfo request) {
	}

	@Override
	public boolean isPresentUserName(String username) {
		return userRepository.findByUsername(username)
			.isPresent();
	}
}
