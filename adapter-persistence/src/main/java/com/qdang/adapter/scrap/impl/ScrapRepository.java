package com.qdang.adapter.scrap.impl;

import com.qdang.adapter.scrap.custom.ScrapRepositoryCustom;
import com.qdang.persistence.scrap.ScrapJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ScrapRepository extends
		Repository<ScrapJpaEntity, Long>,
		ScrapRepositoryCustom {

	Optional<ScrapJpaEntity> findByUserIdAndPostId(Long userId, Long postId);

	void delete(ScrapJpaEntity scrap);

	ScrapJpaEntity save(ScrapJpaEntity scrapJpaEntity);
}
