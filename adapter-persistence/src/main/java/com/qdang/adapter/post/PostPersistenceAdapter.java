package com.qdang.adapter.post;

import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.application.noticeboard.port.out.SavePostPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.post.PostJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class PostPersistenceAdapter implements
		LoadPostPort,
		SavePostPort {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Override
	public List<Post> loadAllFetchHashtagByNoticeBoardId(Long noticeBoardId) {
		return postRepository.findAllFetchHashtagByNoticeBoardId(noticeBoardId)
				.stream()
				.map(postMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public Post loadById(Long id) {
		return postMapper.mapToDomainEntity(
				postRepository.findById(id)
						.orElseThrow(NotFoundPostException::new));
	}

	@Override
	public Post loadFetchHashtagById(Long id) {
		return postMapper.mapToDomainEntity(
				postRepository.findFetchHashtagById(id)
						.orElseThrow(NotFoundPostException::new));
	}

	@Override
	public List<Post> loadByTitleAndContent(String keyword) {
		// Todo : implement
		return null;
	}

	@Override
	public Post save(Post post) {
		PostJpaEntity postJpaEntity = postMapper.mapToJpaEntity(post);
		postRepository.save(postJpaEntity);
		return postMapper.mapToDomainEntity(postJpaEntity);
	}
}
