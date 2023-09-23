package com.qdang.adapter.matchprocess;

import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.matchprocess.domain.MatchProcess;
import com.qdang.application.matchprocess.port.out.SaveMatchProcessPort;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class MatchProcessPersistenceAdapter implements
		SaveMatchProcessPort {

	private final MatchProcessRepository matchProcessRepository;
	private final MatchProcessMapper matchProcessMapper;

	@Override
	public MatchProcess save(MatchProcess matchProcess) {
		MatchProcessJpaEntity matchProcessJpaEntity =
				matchProcessMapper.mapToJpaEntity(matchProcess);
		matchProcessRepository.save(matchProcessJpaEntity);
		matchProcess = matchProcessMapper.mapToDomainEntity(matchProcessJpaEntity);
		return matchProcess;
	}
}
