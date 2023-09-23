package com.qdang.application.user.port.in;

import com.qdang.application.user.port.in.command.LoginCommand;
import com.qdang.application.user.domain.TokenCollection;

public interface LoginUseCase {

	TokenCollection login(LoginCommand command);
}
