package com.qdang.application.user.port.in;

import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.port.in.command.LoginCommand;

public interface LoginUseCase {

	TokenCollection login(LoginCommand command);
}
