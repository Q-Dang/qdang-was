package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Scrap;
import java.util.Optional;

public interface FindScrapPort {

	Optional<Scrap> findByUserIdAndPostId(Long userId, Long postId);
}
