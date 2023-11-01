package com.qdang.application.noticeboard.port.in;

import com.qdang.application.noticeboard.domain.Post;
import java.util.List;

public interface SearchPostUseCase {

	List<Post> searchPost(String keyword);
}
