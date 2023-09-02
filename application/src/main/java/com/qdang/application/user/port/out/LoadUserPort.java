package com.qdang.application.user.port.out;

import com.qdang.application.user.domain.User;
import java.util.List;

public interface LoadUserPort {

	User loadById(Long userId);

	User loadByUsername(String username);

	User loadByLoginId(String loginId);

	List<User> loadAllByMatchId(Long matchId);

	List<User> loadAllContainUsername(String username);
}
