package com.qdang.persistence.match;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "q_match")
@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(nullable = false)
	private MatchTypeJpa matchTypeCode;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false, length = 10)
	private MatchTypeJpa matchTypeName;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean isDeleted;

	@Column(nullable = false)
	private Boolean isValid;

	private LocalDateTime endAt;

	private LocalTime duration;

	@Column(nullable = false)
	@ColumnDefault("1")
	private Integer userCount;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;
}