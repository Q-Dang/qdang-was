package com.qdang.adapter.postlikes.impl;

import com.qdang.adapter.postlikes.custom.PostLikesRepositoryCustom;
import com.qdang.persistence.postlikes.PostLikesJpaEntity;
import org.springframework.data.repository.Repository;

public interface PostLikesRepository extends
		Repository<PostLikesJpaEntity, Long>,
		PostLikesRepositoryCustom {

}
