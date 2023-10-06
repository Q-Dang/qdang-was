package com.qdang.adapter.scrap.impl;

import com.qdang.adapter.scrap.custom.ScrapRepositoryCustom;
import com.qdang.persistence.scrap.ScrapJpaEntity;
import org.springframework.data.repository.Repository;

public interface ScrapRepository extends
		Repository<ScrapJpaEntity, Long>,
		ScrapRepositoryCustom {

}
