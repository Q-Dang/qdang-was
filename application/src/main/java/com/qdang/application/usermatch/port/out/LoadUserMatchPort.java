package com.qdang.application.usermatch.port.out;

import com.qdang.application.usermatch.domain.UserMatch;
import java.util.List;

public interface LoadUserMatchPort {

	List<UserMatch> loadAllByUserId(Long userId);

	List<UserMatch> loadAllByMatchId(Long matchId);
}
