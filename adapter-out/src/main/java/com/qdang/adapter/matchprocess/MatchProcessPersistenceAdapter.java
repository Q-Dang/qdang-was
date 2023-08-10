package com.qdang.adapter.matchprocess;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.matchprocess.port.out.SaveMatchProcessPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MatchProcessPersistenceAdapter implements
		SaveMatchProcessPort {


	@Override
	public MatchProcess save(MatchProcess matchProcess) {
		return null;
	}
}
