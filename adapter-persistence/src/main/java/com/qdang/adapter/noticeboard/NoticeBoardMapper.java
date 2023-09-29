package com.qdang.adapter.noticeboard;

import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;

@Mapper
public class NoticeBoardMapper implements GenericJpaMapper<NoticeBoard, NoticeBoardJpaEntity> {

	@Override
	public NoticeBoard mapToDomainEntity(NoticeBoardJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		NoticeBoard.NoticeBoardBuilder noticeBoard = NoticeBoard.builder();
		noticeBoard.id(jpaEntity.getId());
		noticeBoard.boardName(jpaEntity.getBoardName());
		noticeBoard.anonymousPossible(jpaEntity.getAnonymousPossible());
		noticeBoard.isAdminPossible(jpaEntity.getIsAdminPossible());
		noticeBoard.isManagerPossible(jpaEntity.getIsManagerPossible());
		noticeBoard.isMemberPossible(jpaEntity.getIsMemberPossible());
		noticeBoard.isDeleted(jpaEntity.getIsDeleted());
		noticeBoard.createdAt(jpaEntity.getCreatedAt());
		noticeBoard.updatedAt(jpaEntity.getUpdatedAt());
		return noticeBoard.build();
	}

	@Override
	public NoticeBoardJpaEntity mapToJpaEntity(NoticeBoard domain) {
		if (domain == null) {
			return null;
		}
		NoticeBoardJpaEntity.NoticeBoardJpaEntityBuilder noticeBoardJpaEntity = NoticeBoardJpaEntity.builder();
		noticeBoardJpaEntity.id(domain.getId());
		noticeBoardJpaEntity.boardName(domain.getBoardName());
		noticeBoardJpaEntity.anonymousPossible(domain.getAnonymousPossible());
		noticeBoardJpaEntity.isAdminPossible(domain.getIsAdminPossible());
		noticeBoardJpaEntity.isManagerPossible(domain.getIsManagerPossible());
		noticeBoardJpaEntity.isMemberPossible(domain.getIsMemberPossible());
		noticeBoardJpaEntity.isDeleted(domain.getIsDeleted());
		noticeBoardJpaEntity.createdAt(domain.getCreatedAt());
		noticeBoardJpaEntity.updatedAt(domain.getUpdatedAt());
		return noticeBoardJpaEntity.build();
	}
}
