package com.qdang.api.user.application.port.in;

import com.qdang.api.user.application.port.in.command.UpdateUserProfileCommand;

public interface UpdateUserProfileUseCase {

	void updateUserProfile(UpdateUserProfileCommand command);
}
