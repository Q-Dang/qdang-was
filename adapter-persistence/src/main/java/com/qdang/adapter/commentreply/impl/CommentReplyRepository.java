package com.qdang.adapter.commentreply.impl;

import com.qdang.adapter.commentreply.custom.CommentReplyRepositoryCustom;
import com.qdang.persistence.commentreply.CommentReplyJapEntity;
import org.springframework.data.repository.Repository;

public interface CommentReplyRepository extends
		Repository<CommentReplyJapEntity, Long>,
		CommentReplyRepositoryCustom {

}
