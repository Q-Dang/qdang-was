package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.PostLikes;

public interface DeletePostLikesPort {

	void delete(PostLikes postLikes);
}
