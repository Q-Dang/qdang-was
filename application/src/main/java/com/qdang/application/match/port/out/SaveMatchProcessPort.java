package com.qdang.application.matchprocess.port.out;

import com.qdang.application.matchprocess.domain.MatchProcess;

public interface SaveMatchProcessPort {

	MatchProcess save(MatchProcess matchProcess);
}
