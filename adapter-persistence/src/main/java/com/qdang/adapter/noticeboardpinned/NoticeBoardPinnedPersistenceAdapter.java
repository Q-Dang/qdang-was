package com.qdang.adapter.noticeboardpinned;

import com.qdang.adapter.noticeboardpinned.impl.NoticeBoardPinnedRepository;
import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import com.qdang.application.noticeboard.port.out.DeleteNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.FindNoticeBoardPinnedPort;
import com.qdang.application.noticeboard.port.out.SaveNoticeBoardPinnedPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.noticeboardpinned.NoticeBoardPinnedJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class NoticeBoardPinnedPersistenceAdapter implements
		FindNoticeBoardPinnedPort,
		SaveNoticeBoardPinnedPort,
		DeleteNoticeBoardPinnedPort {

	private final NoticeBoardPinnedRepository noticeBoardPinnedRepository;
	private final NoticeBoardPinnedMapper noticeBoardPinnedMapper;

	@Override
	public Optional<NoticeBoardPinned> findByUserIdAndNoticeBoardId(Long userId,
			Long noticeBoardId) {
		Optional<NoticeBoardPinnedJpaEntity> noticeBoardPinnedJpaEntity =
				noticeBoardPinnedRepository.findByUserIdAndNoticeBoardId(userId, noticeBoardId);
		if (!noticeBoardPinnedJpaEntity.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(
				noticeBoardPinnedMapper.mapToDomainEntity(noticeBoardPinnedJpaEntity.get()));
	}

	@Override
	public NoticeBoardPinned save(NoticeBoardPinned noticeBoardPinned) {
		NoticeBoardPinnedJpaEntity noticeBoardPinnedJpaEntity =
				noticeBoardPinnedMapper.mapToJpaEntity(noticeBoardPinned);
		noticeBoardPinnedRepository.save(noticeBoardPinnedJpaEntity);
		return noticeBoardPinnedMapper.mapToDomainEntity(noticeBoardPinnedJpaEntity);
	}

	@Override
	public void delete(NoticeBoardPinned noticeBoardPinned) {
		NoticeBoardPinnedJpaEntity noticeBoardPinnedJpaEntity =
				noticeBoardPinnedMapper.mapToJpaEntity(noticeBoardPinned);
		noticeBoardPinnedRepository.delete(noticeBoardPinnedJpaEntity);
	}
}
