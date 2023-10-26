package com.qdang.application.noticeboard.port.out;

import com.qdang.application.noticeboard.domain.Hashtag;
import java.util.Optional;

public interface FindHashtagPort {

	Optional<Hashtag> findByName(String name);
}
