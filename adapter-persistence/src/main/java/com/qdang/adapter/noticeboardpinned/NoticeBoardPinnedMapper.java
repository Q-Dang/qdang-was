package com.qdang.adapter.noticeboardpinned;

import com.qdang.adapter.noticeboard.impl.NoticeBoardRepository;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import com.qdang.application.noticeboard.exception.NotFoundNoticeBoardException;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import com.qdang.persistence.noticeboardpinned.NoticeBoardPinnedJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class NoticeBoardPinnedMapper implements GenericJpaMapper<NoticeBoardPinned, NoticeBoardPinnedJpaEntity> {

	private final UserRepository userRepository;
	private final NoticeBoardRepository noticeBoardRepository;

	@Override
	public NoticeBoardPinned mapToDomainEntity(NoticeBoardPinnedJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		NoticeBoardPinned.NoticeBoardPinnedBuilder noticeBoardPinned = NoticeBoardPinned.builder();
		noticeBoardPinned.id(jpaEntity.getId());
		noticeBoardPinned.noticeBoardId(jpaEntity.getNoticeBoard().getId());
		noticeBoardPinned.userId(jpaEntity.getUser().getId());
		return noticeBoardPinned.build();
	}

	@Override
	public NoticeBoardPinnedJpaEntity mapToJpaEntity(NoticeBoardPinned domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(domain.getUserId())
				.orElseThrow(NotFoundUserException::new);
		NoticeBoardJpaEntity noticeBoardJpaEntity = noticeBoardRepository.findById(domain.getNoticeBoardId())
				.orElseThrow(NotFoundNoticeBoardException::new);
		NoticeBoardPinnedJpaEntity.NoticeBoardPinnedJpaEntityBuilder noticeBoardPinnedJpaEntity = NoticeBoardPinnedJpaEntity.builder();
		noticeBoardPinnedJpaEntity.id(domain.getId());
		noticeBoardPinnedJpaEntity.noticeBoard(noticeBoardJpaEntity);
		noticeBoardPinnedJpaEntity.user(userJpaEntity);
		return noticeBoardPinnedJpaEntity.build();
	}
}
