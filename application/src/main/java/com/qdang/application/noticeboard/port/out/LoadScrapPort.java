package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Scrap;
import java.util.Optional;

public interface LoadScrapPort {

	Optional<Scrap> getByUserIdAndNoticeBoardId(Long userId, Long noticeBoardId);
}
