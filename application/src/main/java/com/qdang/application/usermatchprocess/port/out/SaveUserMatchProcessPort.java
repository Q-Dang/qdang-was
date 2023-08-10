package com.qdang.application.usermatchprocess.port.out;

import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import java.util.List;

public interface SaveUserMatchProcessPort {

	UserMatchProcess save(UserMatchProcess userMatchProcess);

	List<UserMatchProcess> saveAll(List<UserMatchProcess> userMatchProcessList);
}
