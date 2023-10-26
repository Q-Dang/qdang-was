package com.qdang.adapter.hashtag.impl;

import com.qdang.persistence.hashtag.HashtagJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface HashtagRepository extends
		Repository<HashtagJpaEntity, Long> {

	Optional<HashtagJpaEntity> findById(Long id);

	Optional<HashtagJpaEntity> findByName(String name);

	HashtagJpaEntity save(HashtagJpaEntity hashtagJpaEntity);
}
