package com.qdang.adapter.user;

import com.qdang.application.user.domain.Gender;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.domain.UserRole;
import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
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

		User.UserBuilder user = User.builder();
		user.id(jpaEntity.getId());
		user.userRole(userRoleToDomain(jpaEntity.getUserRole()));
		user.loginId(jpaEntity.getLoginId());
		user.password(jpaEntity.getPassword());
		user.username(jpaEntity.getUsername());
		user.birthday(jpaEntity.getBirthday());
		user.gender(genderToDomain(jpaEntity.getGender()));
		user.proficiency(jpaEntity.getProficiency());
		user.phone(jpaEntity.getPhone());
		user.fcmToken(jpaEntity.getFcmToken());
		user.profileImage(jpaEntity.getProfileImage());
		user.address(jpaEntity.getAddress());
		user.detailAddress(jpaEntity.getDetailAddress());
		user.isResting(jpaEntity.getIsResting());
		user.isLeaving(jpaEntity.getIsLeaving());
		user.phoneAuthCode(jpaEntity.getPhoneAuthCode());
		user.phoneAuthAt(jpaEntity.getPhoneAuthAt());
		user.isPhoneAuth(jpaEntity.getIsPhoneAuth());
		user.joinAt(jpaEntity.getJoinAt());
		user.joinAgree(jpaEntity.getJoinAgree());
		user.agreeUpdateAt(jpaEntity.getAgreeUpdateAt());
		user.accessAt(jpaEntity.getAccessAt());
		user.accessCount(jpaEntity.getAccessCount());
		user.statusMessage(jpaEntity.getStatusMessage());
		user.average(jpaEntity.getAverage());
		user.matchCount(jpaEntity.getMatchCount());
		user.highRun(jpaEntity.getHighRun());
		user.totalInningCount(jpaEntity.getTotalInningCount());
		user.succeedInningCount(jpaEntity.getSucceedInningCount());
		user.failedInningCount(jpaEntity.getFailedInningCount());
		user.sluggingCount(jpaEntity.getSluggingCount());
		user.battingAverage(jpaEntity.getBattingAverage());
		user.sluggingPercentage(jpaEntity.getSluggingPercentage());
		return user.build();
	}

	@Override
	public UserJpaEntity mapToJpaEntity(User domain) {
		if (domain == null) {
			return null;
		}

		UserJpaEntity.UserJpaEntityBuilder userJpa = UserJpaEntity.builder();
		userJpa.id(domain.getId());
		userJpa.userRole(userRoleToJpa(domain.getUserRole()));
		userJpa.loginId(domain.getLoginId());
		userJpa.password(domain.getPassword());
		userJpa.username(domain.getUsername());
		userJpa.birthday(domain.getBirthday());
		userJpa.gender(genderToJpa(domain.getGender()));
		userJpa.proficiency(domain.getProficiency());
		userJpa.phone(domain.getPhone());
		userJpa.fcmToken(domain.getFcmToken());
		userJpa.profileImage(domain.getProfileImage());
		userJpa.address(domain.getAddress());
		userJpa.detailAddress(domain.getDetailAddress());
		userJpa.isResting(domain.getIsResting());
		userJpa.isLeaving(domain.getIsLeaving());
		userJpa.phoneAuthCode(domain.getPhoneAuthCode());
		userJpa.phoneAuthAt(domain.getPhoneAuthAt());
		userJpa.isPhoneAuth(domain.getIsPhoneAuth());
		userJpa.joinAt(domain.getJoinAt());
		userJpa.joinAgree(domain.getJoinAgree());
		userJpa.agreeUpdateAt(domain.getAgreeUpdateAt());
		userJpa.accessAt(domain.getAccessAt());
		userJpa.accessCount(domain.getAccessCount());
		userJpa.statusMessage(domain.getStatusMessage());
		userJpa.average(domain.getAverage());
		userJpa.matchCount(domain.getMatchCount());
		userJpa.highRun(domain.getHighRun());
		userJpa.totalInningCount(domain.getTotalInningCount());
		userJpa.succeedInningCount(domain.getSucceedInningCount());
		userJpa.failedInningCount(domain.getFailedInningCount());
		userJpa.sluggingCount(domain.getSluggingCount());
		userJpa.battingAverage(domain.getBattingAverage());
		userJpa.sluggingPercentage(domain.getSluggingPercentage());
		return userJpa.build();
	}

	/**
	 * Enum Mapping
	 */
	private Gender genderToDomain(GenderJpa genderJpa) {
		if (genderJpa == null) {
			return null;
		}
		switch (genderJpa) {
			case MALE:
				return Gender.MALE;
			case FEMALE:
				return Gender.FEMALE;
			case OTHER:
				return Gender.OTHER;
			default:
				throw new BusinessException(ErrorType.NOT_FOUND_RESOURCE_EXCEPTION,
						"알 수 없는 유저 성별입니다.");
		}
	}

	private GenderJpa genderToJpa(Gender gender) {
		if (gender == null) {
			return null;
		}
		switch (gender) {
			case MALE:
				return GenderJpa.MALE;
			case FEMALE:
				return GenderJpa.FEMALE;
			case OTHER:
				return GenderJpa.OTHER;
			default:
				throw new BusinessException(ErrorType.NOT_FOUND_RESOURCE_EXCEPTION,
						"알 수 없는 유저 성별입니다.");
		}
	}

	private UserRole userRoleToDomain(UserRoleJpa userRoleJpa) {
		if (userRoleJpa == null) {
			return null;
		}
		switch (userRoleJpa) {
			case MEMBER:
				return UserRole.MEMBER;
			case MANAGER:
				return UserRole.MANAGER;
			case ADMIN:
				return UserRole.ADMIN;
			default:
				throw new BusinessException(ErrorType.NOT_FOUND_RESOURCE_EXCEPTION,
						"알 수 없는 유저 권한입니다.");
		}
	}

	private UserRoleJpa userRoleToJpa(UserRole userRole) {
		if (userRole == null) {
			return null;
		}
		switch (userRole) {
			case MEMBER:
				return UserRoleJpa.MEMBER;
			case MANAGER:
				return UserRoleJpa.MANAGER;
			case ADMIN:
				return UserRoleJpa.ADMIN;
			default:
				throw new BusinessException(ErrorType.NOT_FOUND_RESOURCE_EXCEPTION,
						"알 수 없는 유저 권한입니다.");
		}
	}
}
