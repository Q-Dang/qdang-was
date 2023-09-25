package com.qdang.adapter.matchprocess.custom;

import com.qdang.persistence.matchprocess.MatchProcessJpaEntity;
import java.util.List;

public interface MatchProcessRepositoryCustom {

	List<MatchProcessJpaEntity> findAllByMatchIdAscPhaseCountDescProcessCount(Long matchId);
}
