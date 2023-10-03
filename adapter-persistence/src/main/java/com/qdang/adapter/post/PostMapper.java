package com.qdang.adapter.post;

import com.qdang.adapter.noticeboard.impl.NoticeBoardRepository;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.exception.NotFoundNoticeBoardException;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class PostMapper implements GenericJpaMapper<Post, PostJpaEntity> {

	private final UserRepository userRepository;
	private final NoticeBoardRepository noticeBoardRepository;

	@Override
	public Post mapToDomainEntity(PostJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Post.PostBuilder post = Post.builder();
		post.id(jpaEntity.getId());
		post.noticeBoardId(jpaEntity.getNoticeBoard().getId());
		post.userId(jpaEntity.getUser().getId());
		post.isAnonymous(jpaEntity.getIsAnonymous());
		post.title(jpaEntity.getTitle());
		post.content(jpaEntity.getContent());
		post.isDeleted(jpaEntity.getIsDeleted());
		post.createdAt(jpaEntity.getCreatedAt());
		post.updatedAt(jpaEntity.getUpdatedAt());
		return post.build();
	}

	@Override
	public PostJpaEntity mapToJpaEntity(Post domain) {
		if (domain == null) {
			return null;
		}
		UserJpaEntity userJpaEntity = userRepository.findById(domain.getUserId())
				.orElseThrow(NotFoundUserException::new);
		NoticeBoardJpaEntity noticeBoardJpaEntity = noticeBoardRepository.findById(domain.getNoticeBoardId())
				.orElseThrow(NotFoundNoticeBoardException::new);
		PostJpaEntity.PostJpaEntityBuilder postJpaEntity = PostJpaEntity.builder();
		postJpaEntity.id(domain.getId());
		postJpaEntity.noticeBoard(noticeBoardJpaEntity);
		postJpaEntity.user(userJpaEntity);
		postJpaEntity.isAnonymous(domain.getIsAnonymous());
		postJpaEntity.title(domain.getTitle());
		postJpaEntity.content(domain.getContent());
		postJpaEntity.isDeleted(domain.getIsDeleted());
		postJpaEntity.createdAt(domain.getCreatedAt());
		postJpaEntity.updatedAt(domain.getUpdatedAt());
		return postJpaEntity.build();
	}
}
