package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.PostLikes;
import java.util.Optional;

public interface FindPostLikesPort {

	Optional<PostLikes> findByUserIdAndPostId(Long userId, Long postId);
}
