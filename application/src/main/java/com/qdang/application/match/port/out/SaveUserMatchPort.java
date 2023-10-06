package com.qdang.application.match.port.out;


import com.qdang.application.match.domain.UserMatch;
import java.util.List;

public interface SaveUserMatchPort {

	UserMatch save(UserMatch userMatch);

	List<UserMatch> saveAll(List<UserMatch> userMatches);
}
