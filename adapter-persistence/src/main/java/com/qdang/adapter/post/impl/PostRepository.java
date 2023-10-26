package com.qdang.adapter.post.impl;

import com.qdang.adapter.post.custom.PostRepositoryCustom;
import com.qdang.persistence.post.PostJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PostRepository extends
		Repository<PostJpaEntity, Long>,
		PostRepositoryCustom {

	Optional<PostJpaEntity> findById(Long id);

	@Query("select p "
			+ "from PostJpaEntity p "
			+ "join fetch p.hashtag "
			+ "where p.id = :id")
	Optional<PostJpaEntity> findFetchHashtagById(Long id);

	@Query("select p "
			+ "from PostJpaEntity p "
			+ "join fetch p.hashtag "
			+ "where p.noticeBoard.id = :noticeBoardId")
	List<PostJpaEntity> findAllFetchHashtagByNoticeBoardId(Long noticeBoardId);

	PostJpaEntity save(PostJpaEntity postJpaEntity);
}
