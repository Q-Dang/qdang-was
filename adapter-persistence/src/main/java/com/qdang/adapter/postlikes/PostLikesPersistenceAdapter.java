package com.qdang.adapter.postlikes;

import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.adapter.postlikes.impl.PostLikesRepository;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.noticeboard.port.out.DeletePostLikesPort;
import com.qdang.application.noticeboard.port.out.FindPostLikesPort;
import com.qdang.application.noticeboard.port.out.LoadPostLikesPort;
import com.qdang.application.noticeboard.port.out.SavePostLikesPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.postlikes.PostLikesJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class PostLikesPersistenceAdapter implements
		LoadPostLikesPort,
		FindPostLikesPort,
		SavePostLikesPort,
		DeletePostLikesPort {

	private final PostRepository postRepository;
	private final PostLikesRepository postLikesRepository;
	private final PostLikesMapper postLikesMapper;

	@Override
	public List<PostLikes> loadAllByPostId(Long postId) {
		return postRepository.findById(postId)
				.orElseThrow(NotFoundPostException::new)
				.getPostLikesList()
				.stream()
				.map(postLikesMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<PostLikes> findByUserIdAndPostId(Long userId, Long postId) {
		Optional<PostLikesJpaEntity> postLikesJpaEntity =
				postLikesRepository.findByUserIdAndPostId(userId, postId);
		if (!postLikesJpaEntity.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(
				postLikesMapper.mapToDomainEntity(postLikesJpaEntity.get()));
	}

	@Override
	public PostLikes save(PostLikes postLikes) {
		PostLikesJpaEntity postLikesJpaEntity = postLikesMapper.mapToJpaEntity(postLikes);
		postLikesRepository.save(postLikesJpaEntity);
		return postLikesMapper.mapToDomainEntity(postLikesJpaEntity);
	}

	@Override
	public void delete(PostLikes postLikes) {
		postLikesRepository.delete(
				postLikesMapper.mapToJpaEntity(postLikes));
	}
}
