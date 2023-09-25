package com.qdang.adapter.comment;

import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.comment.CommentJpaEntity;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CommentMapper implements GenericJpaMapper<Comment, CommentJpaEntity> {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Override
	public Comment mapToDomainEntity(CommentJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Comment.CommentBuilder comment = Comment.builder();
		comment.id(jpaEntity.getId());
		comment.userId(jpaEntity.getUser().getId());
		comment.postId(jpaEntity.getPost().getId());
		comment.content(jpaEntity.getContent());
		comment.isDeleted(jpaEntity.getIsDeleted());
		comment.createdAt(jpaEntity.getCreatedAt());
		comment.updatedAt(jpaEntity.getUpdatedAt());
		return comment.build();
	}

	@Override
	public CommentJpaEntity mapToJpaEntity(Comment domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(domain.getUserId())
				.orElseThrow(NotFoundUserException::new);
		PostJpaEntity postJpaEntity = postRepository.findById(domain.getPostId())
				.orElseThrow(NotFoundPostException::new);
		CommentJpaEntity.CommentJpaEntityBuilder commentJpaEntity = CommentJpaEntity.builder();
		commentJpaEntity.id(domain.getId());
		commentJpaEntity.user(userJpaEntity);
		commentJpaEntity.post(postJpaEntity);
		commentJpaEntity.content(domain.getContent());
		commentJpaEntity.isDeleted(domain.getIsDeleted());
		commentJpaEntity.createdAt(domain.getCreatedAt());
		commentJpaEntity.updatedAt(domain.getUpdatedAt());
		return commentJpaEntity.build();
	}
}
