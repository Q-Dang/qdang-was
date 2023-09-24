package com.qdang.application.usermatchprocess.port.out;

import com.qdang.application.usermatchprocess.domain.UserMatchProcess;

public interface LoadUserMatchProcessPort {

	UserMatchProcess loadByUserIdAndMatchProcessId(Long userId, Long matchProcessId);
}
