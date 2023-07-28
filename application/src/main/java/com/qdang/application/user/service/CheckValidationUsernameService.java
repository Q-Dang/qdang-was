package com.qdang.application.user.service;

import com.qdang.application.user.port.in.CheckValidationUsernameUseCase;
import com.qdang.application.user.port.out.CheckUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckValidationUsernameService implements CheckValidationUsernameUseCase {

	private final CheckUserPort checkUserPort;

	@Override
	@Transactional(readOnly = true)
	public Boolean checkValidationUsername(String username) {
		return checkUserPort.hasUserByUsername(username) ? false : true;
	}
}
