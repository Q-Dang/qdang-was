package com.qdang.application.match.port.out;

import com.qdang.application.match.domain.UserMatchProcess;
import java.util.List;

public interface SaveUserMatchProcessPort {

	UserMatchProcess save(UserMatchProcess userMatchProcess);

	List<UserMatchProcess> saveAll(List<UserMatchProcess> userMatchProcessList);
}
