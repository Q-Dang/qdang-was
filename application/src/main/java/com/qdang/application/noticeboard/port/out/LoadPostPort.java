package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Post;
import java.util.List;

public interface LoadPostPort {

	List<Post> loadAllFetchHashtagByNoticeBoardId(Long noticeBoardId);

	Post loadById(Long id);

	Post loadFetchHashtagById(Long id);
}
