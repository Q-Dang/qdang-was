package com.qdang.adapter.user;

import com.qdang.application.user.domain.Gender;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.domain.UserRole;
import com.qdang.global.mapper.Mapper;
import com.qdang.library.mapper.GenericJpaMapper;
import com.qdang.persistence.user.GenderJpa;
import com.qdang.persistence.user.UserJpaEntity;
import com.qdang.persistence.user.UserRoleJpa;

@Mapper
public class UserMapper implements GenericJpaMapper<User, UserJpaEntity> {

	@Override
	public User mapToDomainEntity(UserJpaEntity jpaEntity) {
		if (jpaEntity == null) {
			return null;
		}

		return User.builder()
			.id(jpaEntity.getId())
			.loginId(jpaEntity.getLoginId())
			.password(jpaEntity.getPassword())
			.username(jpaEntity.getUsername())
			.birthday(jpaEntity.getBirthday())
			.gender(genderToDomain(jpaEntity.getGender()))
			.proficiency(jpaEntity.getProficiency())
			.phone(jpaEntity.getPhone())
			.fcmToken(jpaEntity.getFcmToken())
			.profileImage(jpaEntity.getProfileImage())
			.address(jpaEntity.getAddress())
			.detailAddress(jpaEntity.getDetailAddress())
			.isResting(jpaEntity.getIsResting())
			.isLeaving(jpaEntity.getIsLeaving())
			.phoneAuthCode(jpaEntity.getPhoneAuthCode())
			.phoneAuthAt(jpaEntity.getPhoneAuthAt())
			.isPhoneAuth(jpaEntity.getIsPhoneAuth())
			.joinAt(jpaEntity.getJoinAt())
			.joinAgree(jpaEntity.getJoinAgree())
			.agreeUpdateAt(jpaEntity.getAgreeUpdateAt())
			.accessAt(jpaEntity.getAccessAt())
			.accessCount(jpaEntity.getAccessCount())
			.userRole(userRoleToDomain(jpaEntity.getUserRole()))
			.build();
	}

	@Override
	public UserJpaEntity mapToJpaEntity(User domain) {
		if (domain == null) {
			return null;
		}

		return UserJpaEntity.builder()
			.id(domain.getId())
			.loginId(domain.getLoginId())
			.password(domain.getPassword())
			.username(domain.getUsername())
			.birthday(domain.getBirthday())
			.gender(genderToJpa(domain.getGender()))
			.proficiency(domain.getProficiency())
			.phone(domain.getPhone())
			.fcmToken(domain.getFcmToken())
			.profileImage(domain.getProfileImage())
			.address(domain.getAddress())
			.detailAddress(domain.getDetailAddress())
			.isResting(domain.getIsResting())
			.isLeaving(domain.getIsLeaving())
			.phoneAuthCode(domain.getPhoneAuthCode())
			.phoneAuthAt(domain.getPhoneAuthAt())
			.isPhoneAuth(domain.getIsPhoneAuth())
			.joinAt(domain.getJoinAt())
			.joinAgree(domain.getJoinAgree())
			.agreeUpdateAt(domain.getAgreeUpdateAt())
			.accessAt(domain.getAccessAt())
			.accessCount(domain.getAccessCount())
			.userRole(userRoleToJpa(domain.getUserRole()))
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
