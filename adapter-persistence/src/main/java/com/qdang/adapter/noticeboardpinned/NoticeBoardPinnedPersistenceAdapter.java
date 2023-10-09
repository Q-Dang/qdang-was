package com.qdang.adapter.noticeboardpinned;

import com.qdang.adapter.noticeboardpinned.impl.NoticeBoardPinnedRepository;
import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPinnedPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.noticeboardpinned.NoticeBoardPinnedJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class NoticeBoardPinnedPersistenceAdapter implements
		LoadNoticeBoardPinnedPort {

	private final NoticeBoardPinnedRepository noticeBoardPinnedRepository;
	private final NoticeBoardPinnedMapper noticeBoardPinnedMapper;

	@Override
	public Optional<NoticeBoardPinned> getByUserIdAndNoticeBoardId(Long userId,
			Long noticeBoardId) {
		Optional<NoticeBoardPinnedJpaEntity> noticeBoardPinnedJpaEntity =
				noticeBoardPinnedRepository.findByUserIdAndNoticeBoardId(userId, noticeBoardId);
		if (!noticeBoardPinnedJpaEntity.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(noticeBoardPinnedMapper.mapToDomainEntity(noticeBoardPinnedJpaEntity.get()));
	}
}
