package com.qdang.adapter.comment.impl;

import com.qdang.adapter.comment.custom.CommentRepositoryCustom;
import com.qdang.persistence.comment.CommentJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CommentRepository extends
		Repository<CommentJpaEntity, Long>,
		CommentRepositoryCustom {

	Optional<CommentJpaEntity> findById(Long id);
}
