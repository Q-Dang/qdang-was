package com.qdang.api.match.application.port.out;

import com.qdang.api.match.domain.Match;

public interface SaveMatchPort {

	Match save(Match match);
}
