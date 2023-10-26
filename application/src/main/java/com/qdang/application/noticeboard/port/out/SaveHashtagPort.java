package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Hashtag;

public interface SaveHashtagPort {

	Hashtag save(Hashtag hashtag);
}
