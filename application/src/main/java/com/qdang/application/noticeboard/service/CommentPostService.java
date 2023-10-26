package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Comment;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.port.in.CommentPostUseCase;
import com.qdang.application.noticeboard.port.in.command.CommentPostCommand;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.application.noticeboard.port.out.SaveCommentPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CommentPostService implements CommentPostUseCase {

	private final LoadUserPort loadUserPort;
	private final LoadPostPort loadPostPort;
	private final SaveCommentPort saveCommentPort;

	@Override
	@Transactional
	public void commentPost(CommentPostCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		Post post = loadPostPort.loadById(command.getPostId());
		Comment comment = Comment.newComment(
				user,
				post,
				command.getContent(),
				command.getIsAnonymous());
		saveCommentPort.save(comment);
	}
}
