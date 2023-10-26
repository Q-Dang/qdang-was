package com.qdang.adapter.postlikes.impl;

import com.qdang.adapter.postlikes.custom.PostLikesRepositoryCustom;
import com.qdang.persistence.postlikes.PostLikesJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PostLikesRepository extends
		Repository<PostLikesJpaEntity, Long>,
		PostLikesRepositoryCustom {

	Optional<PostLikesJpaEntity> findByUserIdAndPostId(Long userId, Long postId);

	PostLikesJpaEntity save(PostLikesJpaEntity postLikesJpaEntity);

	void delete(PostLikesJpaEntity postLikes);
}
