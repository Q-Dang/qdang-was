package com.qdang.application.match.port.out;

import com.qdang.application.match.domain.MatchProcess;
import java.util.List;

public interface LoadMatchProcessPort {

	 List<MatchProcess> loadAllByMatchIdAscPhaseCountDescProcessCount(Long matchId);
}
