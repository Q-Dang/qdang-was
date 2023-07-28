package com.qdang.application.user.port.out;

import com.qdang.application.user.domain.User;

public interface SaveUserPort {

	User save(User user);
}
