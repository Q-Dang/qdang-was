package com.qdang.application.user.port.in;

import com.qdang.application.user.domain.User;

public interface GetUserProfileUseCase {

	User getUserProfile(Long userId);
}
