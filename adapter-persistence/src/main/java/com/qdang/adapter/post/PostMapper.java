package com.qdang.adapter.post;

import com.qdang.adapter.hashtag.HashtagMapper;
import com.qdang.adapter.hashtag.impl.HashtagRepository;
import com.qdang.adapter.noticeboard.NoticeBoardMapper;
import com.qdang.adapter.noticeboard.impl.NoticeBoardRepository;
import com.qdang.adapter.user.UserMapper;
import com.qdang.adapter.user.impl.UserRepository;
import com.qdang.application.noticeboard.domain.Hashtag;
import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.exception.NotFoundNoticeBoardException;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.exception.NotFoundUserException;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.hashtag.HashtagJpaEntity;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import com.qdang.persistence.post.PostJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class PostMapper implements GenericJpaMapper<Post, PostJpaEntity> {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final NoticeBoardRepository noticeBoardRepository;
	private final NoticeBoardMapper noticeBoardMapper;
	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;

	@Override
	public Post mapToDomainEntity(PostJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}
		Post.PostBuilder post = Post.builder();
		post.id(jpaEntity.getId());
		post.noticeBoard(getNoticeBoard(jpaEntity));
		post.user(getUser(jpaEntity));
		post.hashtag(getHashtag(jpaEntity));
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
		PostJpaEntity.PostJpaEntityBuilder postJpaEntity = PostJpaEntity.builder();
		postJpaEntity.id(domain.getId());
		postJpaEntity.noticeBoard(getNoticeBoardJpaEntity(domain));
		postJpaEntity.user(getUserJpaEntity(domain));
		postJpaEntity.hashtag(getHashtagJpaEntity(domain));
		postJpaEntity.isAnonymous(domain.getIsAnonymous());
		postJpaEntity.title(domain.getTitle());
		postJpaEntity.content(domain.getContent());
		postJpaEntity.isDeleted(domain.getIsDeleted());
		postJpaEntity.createdAt(domain.getCreatedAt());
		postJpaEntity.updatedAt(domain.getUpdatedAt());
		return postJpaEntity.build();
	}

	private User getUser(PostJpaEntity jpaEntity) {
		if (jpaEntity.getUser().getClass() == UserJpaEntity.class) {
			return userMapper.mapToDomainEntity(jpaEntity.getUser());
		}
		return User.init(jpaEntity.getUser().getId());
	}

	private NoticeBoard getNoticeBoard(PostJpaEntity jpaEntity) {
		if (jpaEntity.getNoticeBoard().getClass() == NoticeBoardJpaEntity.class) {
			return noticeBoardMapper.mapToDomainEntity(jpaEntity.getNoticeBoard());
		}
		return NoticeBoard.init(jpaEntity.getNoticeBoard().getId());
	}

	private Hashtag getHashtag(PostJpaEntity jpaEntity) {
		if (jpaEntity.getHashtag() == null) {
			return null;
		}
		if (jpaEntity.getHashtag().getClass() == HashtagJpaEntity.class) {
			return hashtagMapper.mapToDomainEntity(jpaEntity.getHashtag());
		}
		return Hashtag.init(jpaEntity.getHashtag().getId());
	}

	private UserJpaEntity getUserJpaEntity(Post domain) {
		if (domain.getUser() == null) {
			return null;
		}
		return userRepository.findById(domain.getUser().getId())
				.orElseThrow(NotFoundUserException::new);
	}

	private NoticeBoardJpaEntity getNoticeBoardJpaEntity(Post domain) {
		if (domain.getNoticeBoard() == null) {
			return null;
		}
		return noticeBoardRepository.findById(domain.getNoticeBoard().getId())
				.orElseThrow(NotFoundNoticeBoardException::new);
	}

	private HashtagJpaEntity getHashtagJpaEntity(Post domain) {
		if (domain.getHashtag() == null) {
			return null;
		}
		return hashtagRepository.findById(domain.getHashtag().getId())
				.orElseThrow(NotFoundNoticeBoardException::new);
	}
}
