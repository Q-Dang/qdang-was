package qdang.group.was.user.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;
import qdang.group.was.matchProcess.domain.MatchProcess;
import qdang.group.was.userMatch.domain.UserMatch;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "q_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "q_user_id")
	private Long id;


	@OneToMany(mappedBy = "user")
	private List<UserMatch> userMatchList;

	@OneToMany(mappedBy = "user")
	private List<MatchProcess> matchProcessList;

	private String userId;
	private String password;
	private String username;
	private LocalDate birthday;
	private int gender;
	private int proficiency;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	private String phone;
	private String accessToken;
	private String fcmToken;
	private String profileImage;
	private String address;
	private String detailAddress;
	private String joinStaff;
	private String restingTf;
	private String leaveTf;
	private String phoneAuthCode;
	private String phoneAuthAt;
	private String phoneAuthSuccess;
	private String phoneAuthTf;
	private String joinAt;
	private String joinPermitUserId;
	private String joinAgree;
	private String agreeUpdateAt;
	private String accessAt;
	private String accessCount;

	public static User of(
		String userId,
		String password,
		String username,
		LocalDate birthday,
		int gender,
		int proficiency
	) {
		return User.builder()
			.userId(userId)
			.password(password)
			.username(username)
			.birthday(birthday)
			.gender(gender)
			.proficiency(proficiency)
			.build();
	}


}