package com.qdang.core.user;

import com.qdang.core.matchprocess.MatchProcessJpaEntity;
import com.qdang.core.usermatch.UserMatchJpaEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import lombok.*;
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

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	private String loginId;
	private String password;
	private String username;
	private LocalDate birthday;
	private String gender;
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

	@OneToMany(mappedBy = "user")
	private List<UserMatchJpaEntity> userMatchList;

	@OneToMany(mappedBy = "user")
	private List<MatchProcessJpaEntity> matchProcessList;
}