package com.qdang.application.match.port.out;

import com.qdang.application.match.domain.MatchProcess;

public interface SaveMatchProcessPort {

	MatchProcess save(MatchProcess matchProcess);
}
