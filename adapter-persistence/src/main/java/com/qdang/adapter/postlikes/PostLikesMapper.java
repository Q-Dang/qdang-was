package com.qdang.adapter.postlikes;

import com.qdang.adapter.post.PostMapper;
import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.postlikes.PostLikesJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class PostLikesMapper implements GenericJpaMapper<PostLikes, PostLikesJpaEntity> {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Override
	public PostLikes mapToDomainEntity(PostLikesJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		PostLikes.PostLikesBuilder postLikes = PostLikes.builder();
		postLikes.id(jpaEntity.getId());
		postLikes.post(getPost(jpaEntity));
		postLikes.user(getUser(jpaEntity));
		return postLikes.build();
	}

	@Override
	public PostLikesJpaEntity mapToJpaEntity(PostLikes domain) {
		if (domain == null) {
			return null;
		}
		PostLikesJpaEntity.PostLikesJpaEntityBuilder postLikesJpaEntity = PostLikesJpaEntity.builder();
		postLikesJpaEntity.id(domain.getId());
		postLikesJpaEntity.post(getPostJpaEntity(domain));
		postLikesJpaEntity.user(getUserJpaEntity(domain));
		return postLikesJpaEntity.build();
	}

	private User getUser(PostLikesJpaEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private Post getPost(PostLikesJpaEntity jpaEntity) {
		if (jpaEntity.getPost().getClass() == PostJpaEntity.class) {
			return postMapper.mapToDomainEntity(jpaEntity.getPost());
		}
		return Post.init(jpaEntity.getPost().getId());
	}

	private UserJpaEntity getUserJpaEntity(PostLikes domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}

	private PostJpaEntity getPostJpaEntity(PostLikes domain) {
		if (domain.getPost() == null) {
			return null;
		}
		return postRepository.findById(domain.getPost().getId())
				.orElseThrow(NotFoundPostException::new);
	}
}
