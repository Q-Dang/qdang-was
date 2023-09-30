package com.qdang.adapter.noticeboardpinned.impl;

import com.qdang.adapter.noticeboardpinned.custom.NoticeBoardPinnedRepositoryCustom;
import com.qdang.persistence.noticeboardpinned.NoticeBoardPinnedJpaEntity;
import org.springframework.data.repository.Repository;

public interface NoticeBoardPinnedRepository extends
		Repository<NoticeBoardPinnedJpaEntity, Long>,
		NoticeBoardPinnedRepositoryCustom {

}
