package com.qdang.application.user.port.in;


import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.port.in.command.SignUpCommand;

public interface SignUpUseCase {

	TokenCollection signUp(SignUpCommand command);
}
