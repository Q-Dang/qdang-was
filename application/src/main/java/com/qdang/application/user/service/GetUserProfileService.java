package com.qdang.application.user.service;

import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.GetUserProfileUseCase;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetUserProfileService implements GetUserProfileUseCase {

	private final LoadUserPort loadUserPort;

	@Override
	public User getUserProfile(Long userId) {
		return loadUserPort.loadById(userId);
	}
}
