package com.qdang.persistence.user;

import com.qdang.persistence.usermatch.UserMatchJpaEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "q_user", uniqueConstraints = {
		@UniqueConstraint(name = "user_login_id_uq", columnNames = {"loginId"}),
		@UniqueConstraint(name = "user_username_uq", columnNames = {"username"})
})
@DynamicInsert
@DynamicUpdate
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false, length = 10)
	@ColumnDefault("MEMBER")
	private UserRoleJpa userRole;

	@Column(nullable = false, length = 50)
	private String loginId;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length =50)
	private String username;

	private LocalDate birthday;

	@Column(length = 10)
	@Enumerated(value = EnumType.STRING)
	private GenderJpa gender;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer proficiency;

	@Column(length = 13)
	private String phone;

	private String refreshToken;

	private String fcmToken;

	@Column(columnDefinition = "text")
	private String profileImage;

	@Column(length = 100)
	private String address;

	@Column(length = 100)
	private String detailAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "join_staff")
	private UserJpaEntity joinStaff;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean isResting;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean isLeaving;

	@Column(length = 6)
	private String phoneAuthCode;

	private LocalDateTime phoneAuthAt;

	@ColumnDefault("false")
	private Boolean isPhoneAuth;

	@Column(nullable = false)
	@CreatedDate
	// Todo : 가입 시 혹은 인증 시 Join At 업데이트
	private LocalDateTime joinAt;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean joinAgree;

	private LocalDateTime agreeUpdateAt;

	private LocalDateTime accessAt;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer accessCount;

	private String statusMessage;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer average;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer matchCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer highRun;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer totalInningCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer succeedInningCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer failedInningCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer sluggingCount;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer battingAverage;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer sluggingPercentage;

	@OneToMany(mappedBy = "user")
	private List<UserMatchJpaEntity> userMatchList = new ArrayList<>();
}