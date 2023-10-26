package com.qdang.persistence.post;

import com.qdang.persistence.comment.CommentJpaEntity;
import com.qdang.persistence.hashtag.HashtagJpaEntity;
import com.qdang.persistence.noticeboard.NoticeBoardJpaEntity;
import com.qdang.persistence.postlikes.PostLikesJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "q_post")
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notice_board_id", nullable = false)
	private NoticeBoardJpaEntity noticeBoard;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserJpaEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hashtag_id")
	private HashtagJpaEntity hashtag;

	@Column(nullable = false)
	private Boolean isAnonymous;

	@Column(length = 100)
	private String title;

	@Column(columnDefinition = "text")
	private String content;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@BatchSize(size = 100)
	@OneToMany(mappedBy = "post")
	private List<PostLikesJpaEntity> postLikesList = new ArrayList<>();

	@BatchSize(size = 100)
	@OneToMany(mappedBy = "post")
	private List<CommentJpaEntity> comments = new ArrayList<>();
}
