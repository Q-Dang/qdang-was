package com.qdang.adapter.noticeboard.impl;

import com.qdang.adapter.noticeboard.custom.NoticeBoardRepositoryCustom;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface NoticeBoardRepository extends
		Repository<NoticeBoardJpaEntity, Long>,
		NoticeBoardRepositoryCustom {


	Optional<NoticeBoardJpaEntity> findById(Long id);
}
