package com.qdang.application.user.port.in;


import com.qdang.application.user.port.in.command.SignUpCommand;

public interface SignUpUseCase {
	Long signUp(SignUpCommand request);
}
