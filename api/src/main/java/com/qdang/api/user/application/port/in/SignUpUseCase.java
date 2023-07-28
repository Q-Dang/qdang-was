package com.qdang.api.user.application.port.in;

import com.qdang.api.user.application.port.in.command.SignUpCommand;

public interface SignUpUseCase {
	Long signUp(SignUpCommand request);
}
