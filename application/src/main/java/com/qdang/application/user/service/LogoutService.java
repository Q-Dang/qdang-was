package com.qdang.application.user.service;

import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.LogoutUseCase;
import com.qdang.application.user.port.out.DeleteRefreshTokenPort;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.application.user.port.out.SaveUserPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class LogoutService implements LogoutUseCase {

	private final LoadUserPort loadUserPort;
	private final SaveUserPort saveUserPort;
	private final DeleteRefreshTokenPort deleteRefreshTokenPort;

	@Override
	@Transactional
	public void logout(Long userId) {
		User user = loadUserPort.loadById(userId);
		deleteRefreshTokenPort.delete(userId);
		saveUserPort.save(user);
	}
}
