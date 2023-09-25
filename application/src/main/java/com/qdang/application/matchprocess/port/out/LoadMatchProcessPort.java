package com.qdang.application.matchprocess.port.out;

import com.qdang.application.matchprocess.domain.MatchProcess;
import java.util.List;

public interface LoadMatchProcessPort {

	 List<MatchProcess> loadAllByMatchIdAscPhaseCountDescProcessCount(Long matchId);
}
