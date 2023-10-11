package com.qdang.adapter.comment;

import com.qdang.adapter.post.PostMapper;
import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.comment.CommentJpaEntity;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CommentMapper implements
		GenericJpaMapper<Comment, CommentJpaEntity> {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Override
	public Comment mapToDomainEntity(CommentJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Comment.CommentBuilder comment = Comment.builder();
		comment.id(jpaEntity.getId());
		comment.user(getUser(jpaEntity));
		comment.post(getPost(jpaEntity));
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
		CommentJpaEntity.CommentJpaEntityBuilder commentJpaEntity = CommentJpaEntity.builder();
		commentJpaEntity.id(domain.getId());
		commentJpaEntity.user(getUserJpaEntity(domain));
		commentJpaEntity.post(getPostJpaEntity(domain));
		commentJpaEntity.content(domain.getContent());
		commentJpaEntity.isDeleted(domain.getIsDeleted());
		commentJpaEntity.createdAt(domain.getCreatedAt());
		commentJpaEntity.updatedAt(domain.getUpdatedAt());
		return commentJpaEntity.build();
	}

	private User getUser(CommentJpaEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private Post getPost(CommentJpaEntity jpaEntity) {
		if (jpaEntity.getPost().getClass() == PostJpaEntity.class) {
			return postMapper.mapToDomainEntity(jpaEntity.getPost());
		}
		return Post.init(jpaEntity.getPost().getId());
	}

	private UserJpaEntity getUserJpaEntity(Comment domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}

	private PostJpaEntity getPostJpaEntity(Comment domain) {
		if (domain.getPost() == null) {
			return null;
		}
		return postRepository.findById(domain.getPost().getId())
				.orElseThrow(NotFoundPostException::new);
	}
}
