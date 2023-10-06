package com.qdang.adapter.postlikes;

import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
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
	private final PostRepository postRepository;

	@Override
	public PostLikes mapToDomainEntity(PostLikesJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		PostLikes.PostLikesBuilder postLikes = PostLikes.builder();
		postLikes.id(jpaEntity.getId());
		postLikes.postId(jpaEntity.getPost().getId());
		postLikes.userId(jpaEntity.getUser().getId());
		return postLikes.build();
	}

	@Override
	public PostLikesJpaEntity mapToJpaEntity(PostLikes domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(domain.getUserId())
				.orElseThrow(NotFoundUserException::new);
		PostJpaEntity postJpaEntity = postRepository.findById(domain.getPostId())
				.orElseThrow(NotFoundPostException::new);
		PostLikesJpaEntity.PostLikesJpaEntityBuilder postLikesJpaEntity = PostLikesJpaEntity.builder();
		postLikesJpaEntity.id(domain.getId());
		postLikesJpaEntity.post(postJpaEntity);
		postLikesJpaEntity.user(userJpaEntity);
		return postLikesJpaEntity.build();
	}
}
