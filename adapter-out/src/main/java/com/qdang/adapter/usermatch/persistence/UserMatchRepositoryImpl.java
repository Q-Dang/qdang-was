package com.qdang.adapter.usermatch.persistence;

import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMatchRepositoryImpl extends JpaRepository<UserMatchJpaEntity, Long> {

	List<UserMatchJpaEntity> findAllByUserId(Long userId);
}
