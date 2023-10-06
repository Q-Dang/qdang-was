package com.qdang.adapter.matchprocess;

import com.qdang.adapter.matchprocess.impl.MatchProcessRepository;
import com.qdang.application.match.port.out.LoadMatchProcessPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.application.match.domain.MatchProcess;
import com.qdang.application.match.port.out.SaveMatchProcessPort;
import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class MatchProcessPersistenceAdapter implements
		LoadMatchProcessPort,
		SaveMatchProcessPort {

	private final MatchProcessRepository matchProcessRepository;
	private final MatchProcessMapper matchProcessMapper;

	@Override
	public List<MatchProcess> loadAllByMatchIdAscPhaseCountDescProcessCount(Long matchId) {
		return matchProcessRepository
				.findAllByMatchIdAscPhaseCountDescProcessCount(matchId)
				.stream()
				.map(matchProcessMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public MatchProcess save(MatchProcess matchProcess) {
		MatchProcessJpaEntity matchProcessJpaEntity =
				matchProcessMapper.mapToJpaEntity(matchProcess);
		matchProcessRepository.save(matchProcessJpaEntity);
		matchProcess = matchProcessMapper.mapToDomainEntity(matchProcessJpaEntity);
		return matchProcess;
	}
}
