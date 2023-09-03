package com.qdang.application.user.domain;

import com.qdang.application.usermatch.domain.UserMatch;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

	private Long id;
	private UserRole userRole;
	private String loginId;
	private String password;
	private String username;
	private LocalDate birthday;
	private Gender gender;
	private Integer proficiency;
	private String phone;
	private String fcmToken;
	private String profileImage;
	private String address;
	private String detailAddress;
	//	private Integer joinStaffId;
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


	public static User of(
			String loginId,
			String password,
			String username,
			UserRole userRole) {
		return User.builder()
				.loginId(loginId)
				.password(password)
				.username(username)
				.userRole(userRole)
				.build();
	}

	public void updateProfile(
			String username,
			LocalDate birthday,
			Gender gender,
			Integer proficiency) {
		this.username = username;
		this.birthday = birthday;
		this.gender = gender;
		this.proficiency = proficiency;
	}

	public void applyUserMatchRecord(UserMatch userMatch) {
		this.average = (this.average * this.matchCount + userMatch.getAverage()) / (matchCount + 1);
		this.matchCount++;
		this.highRun = Math.max(this.highRun, userMatch.getMaxHighRun());
		this.totalInningCount += userMatch.getInningCount();
		this.succeedInningCount += userMatch.getSucceedInningCount();
		this.failedInningCount += userMatch.getFailedInningCount();
		this.sluggingCount += userMatch.getSluggingCount();
		if (totalInningCount != 0) {
			this.battingAverage = 100 * this.succeedInningCount/ this.totalInningCount;
			this.sluggingPercentage = 100 * this.sluggingCount / this.totalInningCount;
		}
	}

	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}

	public boolean checkPasswordByEncoder(
			String rawPassword,
			PasswordEncoder passwordEncoder) {
		if (passwordEncoder.matches(rawPassword, this.password)) {
			return true;
		}
		return false;
	}
}
