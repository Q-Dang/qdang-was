package com.qdang.persistence.noticeboard;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Entity
@Table(name = "q_notice_board", uniqueConstraints = {
		 @UniqueConstraint(name = "notice_board_board_name_uq", columnNames = {"boardName"})
})
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String boardName;

	@Column(nullable = false)
	@ColumnDefault("true")
	private Boolean anonymousPossible;

	@Column(nullable = false)
	@ColumnDefault("true")
	private Boolean isAdminPossible;

	@Column(nullable = false)
	@ColumnDefault("true")
	private Boolean isManagerPossible;

	@Column(nullable = false)
	@ColumnDefault("true")
	private Boolean isMemberPossible;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;
}
