package com.qdang.adapter.usermatchprocess;

import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;

public interface UserMatchProcessRepository {

	UserMatchProcessJpaEntity save(UserMatchProcessJpaEntity userMatchProcessJpaEntity);
}
