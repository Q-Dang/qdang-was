package com.qdang.application.user.port.out;

import com.qdang.application.user.domain.User;
import java.util.List;

public interface SaveUserPort {

	User save(User user);

	List<User> saveAll(List<User> users);
}
