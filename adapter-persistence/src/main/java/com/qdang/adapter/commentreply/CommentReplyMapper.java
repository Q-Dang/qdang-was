package com.qdang.adapter.commentreply;

import com.qdang.adapter.comment.impl.CommentRepository;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.CommentReply;
import com.qdang.application.noticeboard.exception.NotFoundCommentException;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.comment.CommentJpaEntity;
import com.qdang.persistence.commentreply.CommentReplyJapEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CommentReplyMapper implements GenericJpaMapper<CommentReply, CommentReplyJapEntity> {

	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	@Override
	public CommentReply mapToDomainEntity(CommentReplyJapEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		CommentReply.CommentReplyBuilder commentReply = CommentReply.builder();
		commentReply.id(jpaEntity.getId());
		commentReply.commentId(jpaEntity.getComment().getId());
		commentReply.userId(jpaEntity.getUser().getId());
		commentReply.isDeleted(jpaEntity.getIsDeleted());
		commentReply.createdAt(jpaEntity.getCreatedAt());
		commentReply.updatedAt(jpaEntity.getUpdatedAt());
		return commentReply.build();
	}

	@Override
	public CommentReplyJapEntity mapToJpaEntity(CommentReply domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(domain.getUserId())
				.orElseThrow(NotFoundUserException::new);
		CommentJpaEntity commentJpaEntity = commentRepository.findById(domain.getCommentId())
				.orElseThrow(NotFoundCommentException::new);
		CommentReplyJapEntity.CommentReplyJapEntityBuilder commentReplyJapEntity = CommentReplyJapEntity.builder();
		commentReplyJapEntity.id(domain.getId());
		commentReplyJapEntity.comment(commentJpaEntity);
		commentReplyJapEntity.user(userJpaEntity);
		commentReplyJapEntity.isDeleted(domain.getIsDeleted());
		commentReplyJapEntity.createdAt(domain.getCreatedAt());
		commentReplyJapEntity.updatedAt(domain.getUpdatedAt());
		return commentReplyJapEntity.build();
	}
}
