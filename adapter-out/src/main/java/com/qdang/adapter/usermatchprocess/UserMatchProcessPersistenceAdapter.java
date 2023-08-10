package com.qdang.adapter.usermatchprocess;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.usermatchprocess.domain.UserMatchProcess;
import com.qdang.application.usermatchprocess.port.out.SaveUserMatchProcessPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserMatchProcessPersistenceAdapter implements
		SaveUserMatchProcessPort {


	@Override
	public UserMatchProcess save(UserMatchProcess userMatchProcess) {
		return null;
	}

	@Override
	public List<UserMatchProcess> saveAll(List<UserMatchProcess> userMatchProcessList) {
		return null;
	}
}
