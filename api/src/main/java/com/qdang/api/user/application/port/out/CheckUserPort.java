package com.qdang.api.user.application.port.out;

public interface CheckUserPort {

	boolean hasUserByUsername(String username);
	boolean hasUserByLoginId(String loginId);
}
