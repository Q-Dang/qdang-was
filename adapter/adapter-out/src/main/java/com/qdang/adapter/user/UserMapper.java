package com.qdang.adapter.user;

import com.qdang.application.user.domain.Gender;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.domain.UserRole;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.user.GenderJpa;
import com.qdang.persistence.user.UserJpaEntity;
import com.qdang.persistence.user.UserRoleJpa;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GenericJpaMapper<User, UserJpaEntity> {

	@Override
	public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
		if (userJpaEntity == null) {
			return null;
		}

		return User.builder()
			.id(userJpaEntity.getId())
			.loginId(userJpaEntity.getLoginId())
			.password(userJpaEntity.getPassword())
			.username(userJpaEntity.getUsername())
			.birthday(userJpaEntity.getBirthday())
			.gender(genderToDomain(userJpaEntity.getGender()))
			.proficiency(userJpaEntity.getProficiency())
			.phone(userJpaEntity.getPhone())
			.fcmToken(userJpaEntity.getFcmToken())
			.profileImage(userJpaEntity.getProfileImage())
			.address(userJpaEntity.getAddress())
			.detailAddress(userJpaEntity.getDetailAddress())
			.isResting(userJpaEntity.getIsResting())
			.isLeaving(userJpaEntity.getIsLeaving())
			.phoneAuthCode(userJpaEntity.getPhoneAuthCode())
			.phoneAuthAt(userJpaEntity.getPhoneAuthAt())
			.isPhoneAuth(userJpaEntity.getIsPhoneAuth())
			.joinAt(userJpaEntity.getJoinAt())
			.joinAgree(userJpaEntity.getJoinAgree())
			.agreeUpdateAt(userJpaEntity.getAgreeUpdateAt())
			.accessAt(userJpaEntity.getAccessAt())
			.accessCount(userJpaEntity.getAccessCount())
			.userRole(userRoleToDomain(userJpaEntity.getUserRole()))
			.build();
	}

	@Override
	public UserJpaEntity mapToJpaEntity(User user) {
		if (user == null) {
			return null;
		}

		return UserJpaEntity.builder()
			.id(user.getId())
			.loginId(user.getLoginId())
			.password(user.getPassword())
			.username(user.getUsername())
			.birthday(user.getBirthday())
			.gender(genderToJpa(user.getGender()))
			.proficiency(user.getProficiency())
			.phone(user.getPhone())
			.fcmToken(user.getFcmToken())
			.profileImage(user.getProfileImage())
			.address(user.getAddress())
			.detailAddress(user.getDetailAddress())
			.isResting(user.getIsResting())
			.isLeaving(user.getIsLeaving())
			.phoneAuthCode(user.getPhoneAuthCode())
			.phoneAuthAt(user.getPhoneAuthAt())
			.isPhoneAuth(user.getIsPhoneAuth())
			.joinAt(user.getJoinAt())
			.joinAgree(user.getJoinAgree())
			.agreeUpdateAt(user.getAgreeUpdateAt())
			.accessAt(user.getAccessAt())
			.accessCount(user.getAccessCount())
			.userRole(userRoleToJpa(user.getUserRole()))
			.build();
	}

	private Gender genderToDomain(GenderJpa genderJpa) {
		if ( genderJpa == null ) {
			return null;
		}
		return Gender.valueOf(genderJpa.name());
	}

	private GenderJpa genderToJpa(Gender gender) {
		if ( gender == null ) {
			return null;
		}
		return GenderJpa.valueOf(gender.name());
	}

	private UserRole userRoleToDomain(UserRoleJpa userRoleJpa) {
		if ( userRoleJpa == null ) {
			return null;
		}
		return UserRole.valueOf(userRoleJpa.name());
	}

	private UserRoleJpa userRoleToJpa(UserRole userRole) {
		if ( userRole == null ) {
			return null;
		}
		return UserRoleJpa.valueOf(userRole.name());
	}
}
