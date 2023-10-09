package com.qdang.adapter.noticeboardpinned.impl;

import com.qdang.adapter.noticeboardpinned.custom.NoticeBoardPinnedRepositoryCustom;
import com.qdang.persistence.noticeboardpinned.NoticeBoardPinnedJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface NoticeBoardPinnedRepository extends
		Repository<NoticeBoardPinnedJpaEntity, Long>,
		NoticeBoardPinnedRepositoryCustom {

	@Query("select nbp "
			+ "from NoticeBoardPinnedJpaEntity nbp "
			+ "join nbp.noticeBoard nb "
			+ "join nbp.user u "
			+ "where u.id = :userId "
			+ "and nb.id = :noticeBoardId")
	Optional<NoticeBoardPinnedJpaEntity> findByUserIdAndNoticeBoardId(
			@Param("userId") Long userId,
			@Param("noticeBoardId") Long noticeBoardId);
}
