package com.qdang.adapter.user.custom;

import com.qdang.persistence.user.UserJpaEntity;
import java.util.List;

public interface UserRepositoryCustom {

	List<UserJpaEntity> findAllByMatchId(Long matchId);
}
