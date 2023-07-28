package com.qdang.application.user.port.out;

import com.qdang.application.user.domain.User;
public interface LoadUserPort {

	User loadById(Long userId);
	User loadByUsername(String username);
	User loadByLoginId(String loginId);
}
