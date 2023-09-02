package com.qdang.application.user.port.in;

import com.qdang.application.user.domain.User;
import java.util.List;

public interface SearchUserByUsernameUseCase {

	List<User> searchUserByUsername(String username);
}
