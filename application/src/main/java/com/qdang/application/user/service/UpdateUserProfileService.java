package com.qdang.application.user.service;

import com.qdang.global.usecase.UseCase;
import com.qdang.application.user.port.in.UpdateUserProfileUseCase;
import com.qdang.application.user.port.in.command.UpdateUserProfileCommand;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.application.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateUserProfileService implements UpdateUserProfileUseCase {

	private final LoadUserPort loadUserPort;
	private final SaveUserPort saveUserPort;

	@Override
	@Transactional
	public void updateUserProfile(UpdateUserProfileCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		user.updateProfile(
			command.getUsername(),
			command.getBirthday(),
			command.getGender(),
			command.getProficiency());
		saveUserPort.save(user);
	}
}
