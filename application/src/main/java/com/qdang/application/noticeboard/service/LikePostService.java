package com.qdang.application.noticeboard.service;

import com.qdang.application.noticeboard.domain.Post;
import com.qdang.application.noticeboard.domain.PostLikes;
import com.qdang.application.noticeboard.port.in.LikePostUseCase;
import com.qdang.application.noticeboard.port.in.command.LikePostCommand;
import com.qdang.application.noticeboard.port.out.DeletePostLikesPort;
import com.qdang.application.noticeboard.port.out.FindPostLikesPort;
import com.qdang.application.noticeboard.port.out.LoadPostPort;
import com.qdang.application.noticeboard.port.out.SavePostLikesPort;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import com.qdang.global.usecase.UseCase;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class LikePostService implements LikePostUseCase {

	private final LoadPostPort loadPostPort;
	private final LoadUserPort loadUserPort;
	private final FindPostLikesPort findPostLikesPort;
	private final SavePostLikesPort savePostLikesPort;
	private final DeletePostLikesPort deletePostLikesPort;

	@Override
	@Transactional
	public void likePost(LikePostCommand command) {
		User user = loadUserPort.loadById(command.getUserId());
		Post post = loadPostPort.loadById(command.getPostId());
		Optional<PostLikes> postLikes =
				findPostLikesPort.findByUserIdAndPostId(
						command.getUserId(),
						command.getPostId());
		if (likedPost(command)) {
			if (!postLikes.isPresent()) {
				PostLikes newPostLikes =
						PostLikes.newPostLikes(
								post,
								user);
				savePostLikesPort.save(newPostLikes);
			}
		}
		if (unLikedPost(command)) {
			if (postLikes.isPresent()) {
				deletePostLikesPort.delete(postLikes.get());
			}
		}
	}

	private Boolean likedPost(LikePostCommand command) {
		return command.getLike();
	}

	private Boolean unLikedPost(LikePostCommand command) {
		return !command.getLike();
	}
}
