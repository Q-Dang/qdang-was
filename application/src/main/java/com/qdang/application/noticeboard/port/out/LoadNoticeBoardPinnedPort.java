package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.NoticeBoardPinned;
import java.util.Optional;

public interface LoadNoticeBoardPinnedPort {

	Optional<NoticeBoardPinned> findByUserIdAndNoticeBoardId(Long userId, Long noticeBoardId);
}
