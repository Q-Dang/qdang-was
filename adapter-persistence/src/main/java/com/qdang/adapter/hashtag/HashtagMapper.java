package com.qdang.adapter.hashtag;

import com.qdang.application.noticeboard.domain.Hashtag;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.hashtag.HashtagJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class HashtagMapper implements GenericJpaMapper<Hashtag, HashtagJpaEntity> {

	@Override
	public Hashtag mapToDomainEntity(HashtagJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Hashtag.HashtagBuilder hashtag = Hashtag.builder();
		hashtag.id(jpaEntity.getId());
		hashtag.name(jpaEntity.getName());
		return hashtag.build();
	}

	@Override
	public HashtagJpaEntity mapToJpaEntity(Hashtag domain) {
		if (domain == null) {
			return null;
		}
		HashtagJpaEntity.HashtagJpaEntityBuilder hashtagJpaEntity = HashtagJpaEntity.builder();
		hashtagJpaEntity.id(domain.getId());
		hashtagJpaEntity.name(domain.getName());
		return hashtagJpaEntity.build();
	}
}
