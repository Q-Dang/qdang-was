package com.qdang.adapter.scrap;

import com.qdang.adapter.post.PostMapper;
import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.Scrap;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.scrap.ScrapJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ScrapMapper implements
		GenericJpaMapper<Scrap, ScrapJpaEntity>  {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Override
	public Scrap mapToDomainEntity(ScrapJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Scrap.ScrapBuilder scrap = Scrap.builder();
		scrap.id(jpaEntity.getId());
		scrap.post(getPost(jpaEntity));
		scrap.user(getUser(jpaEntity));
		return scrap.build();
	}

	@Override
	public ScrapJpaEntity mapToJpaEntity(Scrap domain) {
		if (domain == null) {
			return null;
		}
		ScrapJpaEntity.ScrapJpaEntityBuilder scrapJpaEntity = ScrapJpaEntity.builder();
		scrapJpaEntity.id(domain.getId());
		scrapJpaEntity.post(getPostJpaEntity(domain));
		scrapJpaEntity.user(getUserJpaEntity(domain));
		return scrapJpaEntity.build();
	}

	private User getUser(ScrapJpaEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private Post getPost(ScrapJpaEntity jpaEntity) {
		if (jpaEntity.getPost().getClass() == PostJpaEntity.class) {
			return postMapper.mapToDomainEntity(jpaEntity.getPost());
		}
		return Post.init(jpaEntity.getPost().getId());
	}

	private UserJpaEntity getUserJpaEntity(Scrap domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}

	private PostJpaEntity getPostJpaEntity(Scrap domain) {
		if (domain.getPost() == null) {
			return null;
		}
		return postRepository.findById(domain.getPost().getId())
				.orElseThrow(NotFoundPostException::new);
	}
}
