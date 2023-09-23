package com.qdang.application.user.port.out;

public interface CheckUserPort {

	boolean isPresentUsername(String username);
	boolean isPresentLoginId(String loginId);
}
