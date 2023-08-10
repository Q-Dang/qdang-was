package com.qdang.application.usermatch.port.out;


import com.qdang.application.usermatch.domain.UserMatch;

public interface SaveUserMatchPort {

	UserMatch save(UserMatch userMatch);
}
