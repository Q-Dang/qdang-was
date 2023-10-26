package com.qdang.adapter.comment;

import com.qdang.adapter.comment.impl.CommentRepository;
import com.qdang.adapter.post.impl.PostRepository;
import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.exception.NotFoundPostException;
import com.qdang.application.noticeboard.port.out.LoadCommentPort;
import com.qdang.application.noticeboard.port.out.SaveCommentPort;
import com.qdang.global.persistenceadapter.PersistenceAdapter;
import com.qdang.persistence.comment.CommentJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
class CommentPersistenceAdapter implements
		LoadCommentPort,
		SaveCommentPort {

	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;

	@Override
	public List<Comment> loadAllByPostId(Long postId) {
		return postRepository.findById(postId)
				.orElseThrow(NotFoundPostException::new)
				.getComments()
				.stream()
				.map(commentMapper::mapToDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public Comment save(Comment comment) {
		CommentJpaEntity commentJpaEntity = commentMapper.mapToJpaEntity(comment);
		commentRepository.save(commentJpaEntity);
		return commentMapper.mapToDomainEntity(commentJpaEntity);
	}
}
