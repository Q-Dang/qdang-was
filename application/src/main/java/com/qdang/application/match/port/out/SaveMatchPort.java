package com.qdang.application.match.port.out;


import com.qdang.application.match.domain.Match;

public interface SaveMatchPort {

	Match save(Match match);
}
