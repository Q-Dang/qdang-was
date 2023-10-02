package com.qdang.adapter.post.impl;

import com.qdang.adapter.post.custom.PostRepositoryCustom;
import com.qdang.persistence.post.PostJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PostRepository extends
		Repository<PostJpaEntity, Long>,
		PostRepositoryCustom {

	Optional<PostJpaEntity> findById(Long id);
}
