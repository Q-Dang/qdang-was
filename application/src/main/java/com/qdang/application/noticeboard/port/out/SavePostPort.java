package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Post;

public interface SavePostPort {

	Post save(Post post);
}
