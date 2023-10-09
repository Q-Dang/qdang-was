package com.qdang.adapter.noticeboard.impl;

import com.qdang.adapter.noticeboard.custom.NoticeBoardRepositoryCustom;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface NoticeBoardRepository extends
		Repository<NoticeBoardJpaEntity, Long>,
		NoticeBoardRepositoryCustom {

	Optional<NoticeBoardJpaEntity> findById(Long id);

	@Query("select nb "
			+ "from NoticeBoardJpaEntity nb "
			+ "where nb.isDeleted = :isDeleted "
			+ "order by nb.createdAt asc")
	List<NoticeBoardJpaEntity> findAllByIsDeleted(@Param("isDeleted") Boolean isDeleted);
}
