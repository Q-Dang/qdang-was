package com.qdang.adapter.noticeboardpinned;

import com.qdang.adapter.noticeboard.NoticeBoardMapper;
import com.qdang.adapter.noticeboard.impl.NoticeBoardRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import com.qdang.application.noticeboard.exception.NotFoundNoticeBoardException;
import com.qdang.application.user.domain.User;
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
	private final UserMapper userMapper;
	private final NoticeBoardRepository noticeBoardRepository;
	private final NoticeBoardMapper noticeBoardMapper;

	@Override
	public NoticeBoardPinned mapToDomainEntity(NoticeBoardPinnedJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		NoticeBoardPinned.NoticeBoardPinnedBuilder noticeBoardPinned = NoticeBoardPinned.builder();
		noticeBoardPinned.id(jpaEntity.getId());
		noticeBoardPinned.noticeBoard(getNoticeBoard(jpaEntity));
		noticeBoardPinned.user(getUser(jpaEntity));
		return noticeBoardPinned.build();
	}

	@Override
	public NoticeBoardPinnedJpaEntity mapToJpaEntity(NoticeBoardPinned domain) {
		if (domain == null) {
			return null;
		}
		NoticeBoardPinnedJpaEntity.NoticeBoardPinnedJpaEntityBuilder noticeBoardPinnedJpaEntity = NoticeBoardPinnedJpaEntity.builder();
		noticeBoardPinnedJpaEntity.id(domain.getId());
		noticeBoardPinnedJpaEntity.user(getUserJpaEntity(domain));
		noticeBoardPinnedJpaEntity.noticeBoard(getNoticeBoardJpaEntity(domain));
		return noticeBoardPinnedJpaEntity.build();
	}

	private User getUser(NoticeBoardPinnedJpaEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private NoticeBoard getNoticeBoard(NoticeBoardPinnedJpaEntity jpaEntity) {
		if (jpaEntity.getNoticeBoard().getClass() == NoticeBoardJpaEntity.class) {
			return noticeBoardMapper.mapToDomainEntity(jpaEntity.getNoticeBoard());
		}
		return NoticeBoard.init(jpaEntity.getNoticeBoard().getId());
	}

	private UserJpaEntity getUserJpaEntity(NoticeBoardPinned domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}

	private NoticeBoardJpaEntity getNoticeBoardJpaEntity(NoticeBoardPinned domain) {
		if (domain.getNoticeBoard() == null) {
			return null;
		}
		return noticeBoardRepository.findById(domain.getNoticeBoard().getId())
				.orElseThrow(NotFoundNoticeBoardException::new);
	}
}
