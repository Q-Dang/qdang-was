package com.qdang.api.user.application.port.out;

import com.qdang.api.user.domain.User;

public interface LoadUserPort {

	User loadById(Long userId);
	User loadByUsername(String username);
	User loadByLoginId(String loginId);
}
