package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.Scrap;
import com.qdang.application.noticeboard.port.in.ScrapPostUseCase;
import com.qdang.application.noticeboard.port.in.command.ScrapPostCommand;
import com.qdang.application.noticeboard.port.out.DeleteScrapPort;
import com.qdang.application.noticeboard.port.out.FindScrapPort;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.application.noticeboard.port.out.SaveScrapPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class ScrapPostService implements ScrapPostUseCase {

	private final LoadPostPort loadPostPort;
	private final LoadUserPort loadUserPort;
	private final FindScrapPort findScrapPort;
	private final SaveScrapPort saveScrapPort;
	private final DeleteScrapPort deleteScrapPort;

	@Override
	@Transactional
	public void scrapPost(ScrapPostCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		Post post = loadPostPort.loadById(command.getPostId());
		Optional<Scrap> scrap =
				findScrapPort.findByUserIdAndPostId(
						command.getUserId(),
						command.getPostId());
		if (scrapedPost(command)) {
			if (!scrap.isPresent()) {
				Scrap newScrap =
						Scrap.newScrap(
								post,
								user);
				saveScrapPort.save(newScrap);
			}
		}
		if (unScrapedPost(command)) {
			if (scrap.isPresent()) {
				deleteScrapPort.delete(scrap.get());
			}
		}
	}

	private Boolean scrapedPost(ScrapPostCommand command) {
		return command.getScrap();
	}

	private Boolean unScrapedPost(ScrapPostCommand command) {
		return !command.getScrap();
	}
}
