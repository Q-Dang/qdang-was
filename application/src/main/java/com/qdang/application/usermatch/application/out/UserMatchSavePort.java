package com.qdang.application.usermatch.application.out;


import com.qdang.application.usermatch.domain.UserMatch;

public interface UserMatchSavePort {

	UserMatch save(UserMatch userMatch);
}
