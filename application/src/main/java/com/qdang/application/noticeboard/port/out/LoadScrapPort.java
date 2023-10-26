package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Scrap;
import java.util.List;

public interface LoadScrapPort {

	List<Scrap> loadAllByPostId(Long postId);
}
