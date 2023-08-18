package com.qdang.persistence.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@Table(name = "q_user")
@DynamicInsert
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private UserRoleJpa userRole;
	private String loginId;
	private String password;
	private String username;
	private LocalDate birthday;
	@Enumerated(value = EnumType.STRING)
	private GenderJpa gender;
	private Integer proficiency;
	private String phone;
	private String fcmToken;
	private String profileImage;
	private String address;
	private String detailAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "join_staff")
	private UserJpaEntity joinStaff;

	private Boolean isResting;
	private Boolean isLeaving;
	private String phoneAuthCode;
	private LocalDateTime phoneAuthAt;
	private Boolean isPhoneAuth;

	private LocalDateTime joinAt;
	private Boolean joinAgree;
	private LocalDateTime agreeUpdateAt;
	private LocalDateTime accessAt;
	private Integer accessCount;

	private String statusMessage;
	private Integer average;
	private Integer matchCount;
	private Integer highRun;
	private Integer totalInningCount;
	private Integer succeedInningCount;
	private Integer failedInningCount;
	private Integer sluggingCount;
	private Integer battingAverage;
	private Integer sluggingPercentage;


	/**
	 * mappedBy 지양! -> 할 거면 set, change 함수 만들어서 사용하기
	 */
//	@OneToMany(mappedBy = "user")
//	private List<UserMatchJpaEntity> userMatchList = new ArrayList<>();
//
//	@OneToMany(mappedBy = "player")
//	private List<MatchProcessJpaEntity> matchProcessList = new ArrayList<>();
}