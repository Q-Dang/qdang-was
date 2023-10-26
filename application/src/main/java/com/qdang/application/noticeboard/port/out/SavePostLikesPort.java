package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.PostLikes;

public interface SavePostLikesPort {

	PostLikes save(PostLikes postLikes);
}
