package com.qdang.adapter.commentreply;

import com.qdang.adapter.comment.CommentMapper;
import com.qdang.adapter.comment.impl.CommentRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.CommentReply;
import com.qdang.application.noticeboard.exception.NotFoundCommentException;
import com.qdang.application.user.domain.User;
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
	private final UserMapper userMapper;
	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;

	@Override
	public CommentReply mapToDomainEntity(CommentReplyJapEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		CommentReply.CommentReplyBuilder commentReply = CommentReply.builder();
		commentReply.id(jpaEntity.getId());
		commentReply.comment(getComment(jpaEntity));
		commentReply.user(getUser(jpaEntity));
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
		CommentReplyJapEntity.CommentReplyJapEntityBuilder commentReplyJapEntity = CommentReplyJapEntity.builder();
		commentReplyJapEntity.id(domain.getId());
		commentReplyJapEntity.comment(getCommentJpaEntity(domain));
		commentReplyJapEntity.user(getUserJpaEntity(domain));
		commentReplyJapEntity.isDeleted(domain.getIsDeleted());
		commentReplyJapEntity.createdAt(domain.getCreatedAt());
		commentReplyJapEntity.updatedAt(domain.getUpdatedAt());
		return commentReplyJapEntity.build();
	}

	private User getUser(CommentReplyJapEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private Comment getComment(CommentReplyJapEntity jpaEntity) {
		if (jpaEntity.getComment().getClass() == CommentJpaEntity.class) {
			return commentMapper.mapToDomainEntity(jpaEntity.getComment());
		}
		return Comment.init(jpaEntity.getComment().getId());
	}

	private UserJpaEntity getUserJpaEntity(CommentReply domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}

	private CommentJpaEntity getCommentJpaEntity(CommentReply domain) {
		if (domain.getComment() == null) {
			return null;
		}
		return commentRepository.findById(domain.getComment().getId())
				.orElseThrow(NotFoundCommentException::new);
	}
}
