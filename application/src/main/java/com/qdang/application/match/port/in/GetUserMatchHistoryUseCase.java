package com.qdang.application.match.port.in;

import com.qdang.application.match.domain.MatchHistory;
import java.util.List;

public interface GetUserMatchHistoryUseCase {

	List<MatchHistory> getMatchHistoryByUserId(Long userId);
}
