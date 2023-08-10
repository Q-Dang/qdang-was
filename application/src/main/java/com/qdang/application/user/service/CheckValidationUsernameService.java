package com.qdang.application.user.service;

import com.qdang.global.usecase.UseCase;
import com.qdang.application.user.port.in.CheckValidationUsernameUseCase;
import com.qdang.application.user.port.out.CheckUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CheckValidationUsernameService implements CheckValidationUsernameUseCase {

	private final CheckUserPort checkUserPort;

	@Override
	@Transactional(readOnly = true)
	public Boolean checkValidationUsername(String username) {
		return checkUserPort.hasUserByUsername(username) ? false : true;
	}
}
