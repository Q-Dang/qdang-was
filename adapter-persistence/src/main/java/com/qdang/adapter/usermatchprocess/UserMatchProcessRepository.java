package com.qdang.adapter.usermatchprocess;

import com.qdang.persistence.usermatchprocess.UserMatchProcessJpaEntity;
import java.util.List;

public interface UserMatchProcessRepository {

	UserMatchProcessJpaEntity save(UserMatchProcessJpaEntity userMatchProcessJpaEntity);

	List<UserMatchProcessJpaEntity> saveAll(List<UserMatchProcessJpaEntity> userMatchProcessJpaEntity);
}
