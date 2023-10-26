package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.PostLikes;
import java.util.List;

public interface LoadPostLikesPort {

	List<PostLikes> loadAllByPostId(Long postId);
}
