package com.qdang.api.userMatch.application.out;

import com.qdang.api.userMatch.domain.UserMatch;

public interface UserMatchSavePort {

	UserMatch save(UserMatch userMatch);
}
