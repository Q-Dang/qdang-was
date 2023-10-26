package com.qdang.adapter.hashtag;

import com.qdang.adapter.hashtag.impl.HashtagRepository;
import com.qdang.application.noticeboard.domain.Hashtag;
import com.qdang.application.noticeboard.port.out.FindHashtagPort;
import com.qdang.application.noticeboard.port.out.SaveHashtagPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.hashtag.HashtagJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class HashtagPersistenceAdapter implements
		FindHashtagPort,
		SaveHashtagPort {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;

	@Override
	public Optional<Hashtag> findByName(String name) {
		Optional<HashtagJpaEntity> hashtagJpaEntity =
				hashtagRepository.findByName(name);
		if (!hashtagJpaEntity.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(
				hashtagMapper.mapToDomainEntity(hashtagJpaEntity.get()));
	}

	@Override
	public Hashtag save(Hashtag hashtag) {
		HashtagJpaEntity hashtagJpaEntity = hashtagMapper.mapToJpaEntity(hashtag);
		hashtagRepository.save(hashtagJpaEntity);
		return hashtagMapper.mapToDomainEntity(hashtagJpaEntity);
	}
}
