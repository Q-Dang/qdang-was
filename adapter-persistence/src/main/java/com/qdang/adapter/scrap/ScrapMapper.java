package com.qdang.adapter.scrap;

import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Scrap;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.scrap.ScrapJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class ScrapMapper implements GenericJpaMapper<Scrap, ScrapJpaEntity>  {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Override
	public Scrap mapToDomainEntity(ScrapJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Scrap.ScrapBuilder scrap = Scrap.builder();
		scrap.id(jpaEntity.getId());
		scrap.postId(jpaEntity.getPost().getId());
		scrap.userId(jpaEntity.getUser().getId());
		return scrap.build();
	}

	@Override
	public ScrapJpaEntity mapToJpaEntity(Scrap domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(domain.getUserId())
				.orElseThrow(NotFoundUserException::new);
		PostJpaEntity postJpaEntity = postRepository.findById(domain.getPostId())
				.orElseThrow(NotFoundPostException::new);
		ScrapJpaEntity.ScrapJpaEntityBuilder scrapJpaEntity = ScrapJpaEntity.builder();
		scrapJpaEntity.id(domain.getId());
		scrapJpaEntity.post(postJpaEntity);
		scrapJpaEntity.user(userJpaEntity);
		return scrapJpaEntity.build();
	}
}
