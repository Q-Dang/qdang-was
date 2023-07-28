package com.qdang.api.user.application.port.in;

import com.qdang.api.user.application.port.in.command.LoginCommand;
import com.qdang.api.user.domain.TokenCollection;

public interface LoginUseCase {

	TokenCollection login(LoginCommand request);
}
