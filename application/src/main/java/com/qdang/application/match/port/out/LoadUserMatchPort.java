package com.qdang.application.match.port.out;

import com.qdang.application.match.domain.UserMatch;
import java.util.List;

public interface LoadUserMatchPort {

	List<UserMatch> loadAllByUserId(Long userId);

	List<UserMatch> loadAllByMatchId(Long matchId);
}
