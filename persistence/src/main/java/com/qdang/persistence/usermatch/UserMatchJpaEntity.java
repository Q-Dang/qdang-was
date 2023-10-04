package com.qdang.persistence.usermatch;

import com.qdang.persistence.match.MatchJpaEntity;
import com.qdang.persistence.user.UserJpaEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Entity
@Table(name = "q_user_match")
@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatchJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserJpaEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id", nullable = false)
	private MatchJpaEntity match;

	@Column(nullable = false)
	private Integer targetScore;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer finishCushionTargetScore;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer finishBankShotTargetScore;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	protected LocalDateTime createdAt;

	@LastModifiedDate
	protected LocalDateTime updatedAt;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;

	private Integer score;
	private Integer finishCushionScore;
	private Integer finishBankShotScore;

	private Integer ranking;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer maxHighRun;

	private Integer average;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer inningCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer succeedInningCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer failedInningCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer sluggingCount;
}
