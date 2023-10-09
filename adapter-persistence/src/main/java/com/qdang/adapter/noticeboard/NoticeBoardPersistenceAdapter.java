package com.qdang.adapter.noticeboard;

import com.qdang.adapter.noticeboard.impl.NoticeBoardRepository;
import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class NoticeBoardPersistenceAdapter implements
		LoadNoticeBoardPort {

	private final NoticeBoardRepository noticeBoardRepository;
	private final NoticeBoardMapper noticeBoardMapper;

	@Override
	public List<NoticeBoard> loadAllNotDeleted() {
		List<NoticeBoardJpaEntity> noticeBoardJpaEntities = noticeBoardRepository.findAllByIsDeleted(false);
		return noticeBoardJpaEntities
				.stream()
				.map(noticeBoardMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}
}
