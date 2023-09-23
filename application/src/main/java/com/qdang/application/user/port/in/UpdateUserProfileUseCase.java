package com.qdang.application.user.port.in;


import com.qdang.application.user.port.in.command.UpdateUserProfileCommand;

public interface UpdateUserProfileUseCase {

	void updateUserProfile(UpdateUserProfileCommand command);
}
