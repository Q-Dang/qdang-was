package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Hashtag;
import com.qdang.application.noticeboard.domain.NoticeBoard;
import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.port.in.WritePostUseCase;
import com.qdang.application.noticeboard.port.in.command.WritePostCommand;
import com.qdang.application.noticeboard.port.out.FindHashtagPort;
import com.qdang.application.noticeboard.port.out.LoadNoticeBoardPort;
import com.qdang.application.noticeboard.port.out.SaveHashtagPort;
import com.qdang.application.noticeboard.port.out.SavePostPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class WritePostService implements WritePostUseCase {

	private final LoadUserPort loadUserPort;
	private final LoadNoticeBoardPort loadNoticeBoardPort;
	private final FindHashtagPort findHashtagPort;
	private final SaveHashtagPort saveHashtagPort;
	private final SavePostPort savePostPort;

	@Override
	@Transactional
	public void writePost(WritePostCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		NoticeBoard noticeBoard = loadNoticeBoardPort.loadById(command.getNoticeBoardId());
		Hashtag hashtag = findHashtagPort.findByName(command.getHashtag())
				.orElseGet(() -> saveHashtagPort.save(Hashtag.newHashtag(command.getHashtag())));
		savePostPort.save(
				Post.newPost(
						noticeBoard,
						user,
						hashtag,
						command.getIsAnonymous(),
						command.getTitle(),
						command.getContent()
				));
	}
}
