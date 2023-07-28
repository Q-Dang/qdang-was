package com.qdang.api.user.application.service;

import com.qdang.api.user.application.port.in.SignUpUseCase;
import com.qdang.api.user.application.port.in.command.SignUpCommand;
import com.qdang.api.user.application.port.out.CheckUserPort;
import com.qdang.api.user.application.port.out.SaveUserPort;
import com.qdang.api.user.domain.User;
import com.qdang.api.user.exception.ConflictUserNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

	private final PasswordEncoder passwordEncoder;
	private final SaveUserPort saveUserPort;
	private final CheckUserPort checkUserPort;

	@Override
	@Transactional
	public Long signUp(SignUpCommand request) {
		if (checkUserPort.hasUserByLoginId(request.getLoginId())) {
			throw new ConflictUserNameException();
		}
		User user = User.of(request.getLoginId(), request.getPassword());
		user.encodePassword(passwordEncoder);
		saveUserPort.save(user);
		return user.getId();
	}
}
