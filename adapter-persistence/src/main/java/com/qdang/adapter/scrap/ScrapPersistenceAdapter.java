package com.qdang.adapter.scrap;

import com.qdang.adapter.scrap.impl.ScrapRepository;
import com.qdang.application.noticeboard.domain.Scrap;
import com.qdang.application.noticeboard.port.out.DeleteScrapPort;
import com.qdang.application.noticeboard.port.out.FindScrapPort;
import com.qdang.application.noticeboard.port.out.SaveScrapPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.scrap.ScrapJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class ScrapPersistenceAdapter implements
		FindScrapPort,
		SaveScrapPort,
		DeleteScrapPort {

	private final ScrapRepository scrapRepository;
	private final ScrapMapper scrapMapper;

	@Override
	public Optional<Scrap> findByUserIdAndPostId(Long userId, Long postId) {
		Optional<ScrapJpaEntity> scrapJpaEntity =
				scrapRepository.findByUserIdAndPostId(userId, postId);
		if (!scrapJpaEntity.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(
				scrapMapper.mapToDomainEntity(scrapJpaEntity.get()));
	}

	@Override
	public Scrap save(Scrap scrap) {
		ScrapJpaEntity scrapJpaEntity = scrapMapper.mapToJpaEntity(scrap);
		scrapRepository.save(scrapJpaEntity);
		return scrapMapper.mapToDomainEntity(scrapJpaEntity);
	}

	@Override
	public void delete(Scrap scrap) {
		scrapRepository.delete(
				scrapMapper.mapToJpaEntity(scrap));
	}
}
