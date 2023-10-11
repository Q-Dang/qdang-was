package com.qdang.adapter.usermatch.custom;

import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;

public interface UserMatchRepositoryCustom {

	List<UserMatchJpaEntity> findAllByMatchIdFetchUser(Long id);
}
