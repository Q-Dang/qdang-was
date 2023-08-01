package com.qdang.application.user.service;

import com.qdang.application.user.port.in.UpdateUserProfileUseCase;
import com.qdang.application.user.port.in.command.UpdateUserProfileCommand;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.application.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileService implements UpdateUserProfileUseCase {

	private final LoadUserPort loadUserPort;
	private final SaveUserPort saveUserPort;

	@Override
	@Transactional
	public void updateUserProfile(UpdateUserProfileCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		user.update(
			command.getUsername(),
			command.getBirthday(),
			command.getGender(),
			command.getProficiency());
		saveUserPort.save(user);
	}
}
