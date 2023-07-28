package com.qdang.application.user.port.out;

public interface CheckUserPort {

	boolean hasUserByUsername(String username);
	boolean hasUserByLoginId(String loginId);
}
