package com.qdang.adapter.usermatch;

import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;

public interface UserMatchRepository {

	UserMatchJpaEntity save(UserMatchJpaEntity userMatchJpaEntity);

	List<UserMatchJpaEntity> findAllByUserId(Long userId);
}
