package com.qdang.adapter.scrap;

import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.scrap.impl.ScrapRepository;
import com.qdang.application.noticeboard.domain.Scrap;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.noticeboard.port.out.DeleteScrapPort;
import com.qdang.application.noticeboard.port.out.FindScrapPort;
import com.qdang.application.noticeboard.port.out.LoadScrapPort;
import com.qdang.application.noticeboard.port.out.SaveScrapPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.scrap.ScrapJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class ScrapPersistenceAdapter implements
		LoadScrapPort,
		FindScrapPort,
		SaveScrapPort,
		DeleteScrapPort {

	private final PostRepository postRepository;
	private final ScrapRepository scrapRepository;
	private final ScrapMapper scrapMapper;

	@Override
	public List<Scrap> loadAllByPostId(Long postId) {
		return postRepository.findById(postId)
				.orElseThrow(NotFoundPostException::new)
				.getScraps()
				.stream()
				.map(scrapMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

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
