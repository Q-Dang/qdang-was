package com.qdang.persistence.user;

import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "q_user", uniqueConstraints = {
		@UniqueConstraint(name = "user_login_id_uq", columnNames = {"loginId"}),
		@UniqueConstraint(name = "user_username_uq", columnNames = {"username"})
})
@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private UserRoleJpa userRole;

	@Column(nullable = false)
	private String loginId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String username;

	private LocalDate birthday;
	@Enumerated(value = EnumType.STRING)
	private GenderJpa gender;

	@Column(nullable = false)
	private Integer proficiency;
	private String phone;
	private String fcmToken;
	private String profileImage;
	private String address;
	private String detailAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "join_staff")
	private UserJpaEntity joinStaff;

	@Column(nullable = false)
	private Boolean isResting;

	@Column(nullable = false)
	private Boolean isLeaving;
	private String phoneAuthCode;
	private LocalDateTime phoneAuthAt;
	private Boolean isPhoneAuth;

	@Column(nullable = false)
	private LocalDateTime joinAt;

	@Column(nullable = false)
	private Boolean joinAgree;
	private LocalDateTime agreeUpdateAt;
	private LocalDateTime accessAt;

	@Column(nullable = false)
	private Integer accessCount;

	private String statusMessage;

	@Column(nullable = false)
	private Integer average;

	@Column(nullable = false)
	private Integer matchCount;

	@Column(nullable = false)
	private Integer highRun;

	@Column(nullable = false)
	private Integer totalInningCount;

	@Column(nullable = false)
	private Integer succeedInningCount;

	@Column(nullable = false)
	private Integer failedInningCount;

	@Column(nullable = false)
	private Integer sluggingCount;

	@Column(nullable = false)
	private Integer battingAverage;

	@Column(nullable = false)
	private Integer sluggingPercentage;

	@OneToMany(mappedBy = "user")
	private List<UserMatchJpaEntity> userMatchList = new ArrayList<>();

//	@OneToMany(mappedBy = "player")
//	private List<MatchProcessJpaEntity> matchProcessList = new ArrayList<>();
}