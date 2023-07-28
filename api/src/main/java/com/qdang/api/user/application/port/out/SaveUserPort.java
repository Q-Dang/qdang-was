package com.qdang.api.user.application.port.out;

import com.qdang.api.user.domain.User;

public interface SaveUserPort {

	User save(User user);
}
