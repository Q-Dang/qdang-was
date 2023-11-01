package com.qdang.application.user.service;

import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.SearchUserByUsernameUseCase;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
class SearchUserByUsernameService implements SearchUserByUsernameUseCase {

	private final LoadUserPort loadUserPort;

	@Override
	@Transactional(readOnly = true)
	public List<User> searchUserByUsername(String username) {
		return loadUserPort.loadAllContainUsername(username);
	}
}
