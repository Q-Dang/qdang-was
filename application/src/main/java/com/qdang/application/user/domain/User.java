package com.qdang.application.user.domain;

import com.qdang.application.match.domain.UserMatch;
import com.qdang.application.user.exception.InvalidPasswordException;
import com.qdang.global.exception.InvalidException;
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
	private String refreshToken;
	private String fcmToken;
	private String profileImage;
	private String address;
	private String detailAddress;
	// Todo : fix Mapper - Join Staff
	private User joinStaff;
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

	public static User init(Long id) {
		return User.builder()
				.id(id)
				.build();
	}


	public static User newUser(
			String loginId,
			String password,
			String username,
			UserRole userRole) {
		return User.builder()
				.loginId(loginId)
				.password(password)
				.username(username)
				.userRole(userRole)
				.joinAt(LocalDateTime.now())
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

	public void reflectMatchResult(UserMatch userMatch) {
		this.average = (this.average * this.matchCount + userMatch.getAverage()) / (matchCount + 1);
		this.matchCount++;
		this.highRun = Math.max(this.highRun, userMatch.getMaxHighRun());
		this.totalInningCount += userMatch.getInningCount();
		this.succeedInningCount += userMatch.getSucceedInningCount();
		this.failedInningCount += userMatch.getFailedInningCount();
		this.sluggingCount += userMatch.getSluggingCount();
		if (totalInningCount != 0) {
			this.battingAverage = 100 * this.succeedInningCount / this.totalInningCount;
			this.sluggingPercentage = 100 * this.sluggingCount / this.totalInningCount;
		}
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void checkPasswordByEncoder(
			String rawPassword,
			PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(rawPassword, password)) {
			throw new InvalidPasswordException();
		}
	}

	public void logout() {
		refreshToken = null;
	}

	public void validateRefreshToken(String refreshToken) {
		if (!this.refreshToken.equals(refreshToken)) {
			throw new InvalidException("올바르지 않은 리프레시 토큰입니다.");
		}
	}

	public void validateLongin() {
		if (refreshToken == null) {
			throw new InvalidException("로그인이 안 된 유저입니다.");
		}
	}
}
