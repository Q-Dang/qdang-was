package com.qdang.application.usermatch.port.out;


import com.qdang.application.usermatch.domain.UserMatch;
import java.util.List;

public interface SaveUserMatchPort {

	UserMatch save(UserMatch userMatch);

	List<UserMatch> saveAll(List<UserMatch> userMatches);
}
