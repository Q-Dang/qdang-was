package com.qdang.api.user.domain;

import com.qdang.api.user.exception.InvalidPasswordException;
import com.qdang.core.user.UserRole;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

// TODO: Domain 모델로 고치기
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
	private String gender;
	private Integer proficiency;
	private String phone;
	private String fcmToken;
	private String profileImage;
	private String address;
	private String detailAddress;
	private Integer matchCount;
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

	public static User of(
		String loginId,
		String password) {
		return User.builder()
			.loginId(loginId)
			.password(password)
			.build();
	}

	public void update(
		String username,
		LocalDate birthday,
		String gender,
		Integer proficiency) {
		this.username = username;
		this.birthday = birthday;
		this.gender = gender;
		this.proficiency = proficiency;
	}

	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}

	public void checkPasswordByEncoder(
		String rawPassword,
		PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(rawPassword, this.password)) {
			throw new InvalidPasswordException();
		}
	}
}
