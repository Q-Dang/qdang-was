package com.qdang.application.match.port.out;

import com.qdang.application.match.domain.UserMatchProcess;

public interface LoadUserMatchProcessPort {

	UserMatchProcess loadByUserIdAndMatchProcessId(Long userId, Long matchProcessId);
}
