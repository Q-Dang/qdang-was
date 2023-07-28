package com.qdang.api.user.adapter.out;

import com.qdang.application.user.domain.User;
import com.qdang.application.user.domain.UserRole;
import com.qdang.persistence.user.UserJpaEntity;
import com.qdang.persistence.user.UserRoleJpa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-28T16:30:52+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToDomainEntity(UserJpaEntity arg0) {
        if ( arg0 == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( arg0.getId() );
        user.userRole( userRoleJpaToUserRole( arg0.getUserRole() ) );
        user.loginId( arg0.getLoginId() );
        user.password( arg0.getPassword() );
        user.username( arg0.getUsername() );
        user.birthday( arg0.getBirthday() );
        user.gender( arg0.getGender() );
        user.proficiency( arg0.getProficiency() );
        user.phone( arg0.getPhone() );
        user.fcmToken( arg0.getFcmToken() );
        user.profileImage( arg0.getProfileImage() );
        user.address( arg0.getAddress() );
        user.detailAddress( arg0.getDetailAddress() );
        user.isResting( arg0.getIsResting() );
        user.isLeaving( arg0.getIsLeaving() );
        user.phoneAuthCode( arg0.getPhoneAuthCode() );
        user.phoneAuthAt( arg0.getPhoneAuthAt() );
        user.isPhoneAuth( arg0.getIsPhoneAuth() );
        user.joinAt( arg0.getJoinAt() );
        user.joinAgree( arg0.getJoinAgree() );
        user.agreeUpdateAt( arg0.getAgreeUpdateAt() );
        user.accessAt( arg0.getAccessAt() );
        user.accessCount( arg0.getAccessCount() );

        return user.build();
    }

    @Override
    public UserJpaEntity mapToJpaEntity(User arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserJpaEntity.UserJpaEntityBuilder userJpaEntity = UserJpaEntity.builder();

        userJpaEntity.id( arg0.getId() );
        userJpaEntity.userRole( userRoleToUserRoleJpa( arg0.getUserRole() ) );
        userJpaEntity.loginId( arg0.getLoginId() );
        userJpaEntity.password( arg0.getPassword() );
        userJpaEntity.username( arg0.getUsername() );
        userJpaEntity.birthday( arg0.getBirthday() );
        userJpaEntity.gender( arg0.getGender() );
        userJpaEntity.proficiency( arg0.getProficiency() );
        userJpaEntity.phone( arg0.getPhone() );
        userJpaEntity.fcmToken( arg0.getFcmToken() );
        userJpaEntity.profileImage( arg0.getProfileImage() );
        userJpaEntity.address( arg0.getAddress() );
        userJpaEntity.detailAddress( arg0.getDetailAddress() );
        userJpaEntity.isResting( arg0.getIsResting() );
        userJpaEntity.isLeaving( arg0.getIsLeaving() );
        userJpaEntity.phoneAuthCode( arg0.getPhoneAuthCode() );
        userJpaEntity.phoneAuthAt( arg0.getPhoneAuthAt() );
        userJpaEntity.isPhoneAuth( arg0.getIsPhoneAuth() );
        userJpaEntity.joinAt( arg0.getJoinAt() );
        userJpaEntity.joinAgree( arg0.getJoinAgree() );
        userJpaEntity.agreeUpdateAt( arg0.getAgreeUpdateAt() );
        userJpaEntity.accessAt( arg0.getAccessAt() );
        userJpaEntity.accessCount( arg0.getAccessCount() );

        return userJpaEntity.build();
    }

    protected UserRole userRoleJpaToUserRole(UserRoleJpa userRoleJpa) {
        if ( userRoleJpa == null ) {
            return null;
        }

        UserRole userRole;

        switch ( userRoleJpa ) {
            case MEMBER: userRole = UserRole.MEMBER;
            break;
            case ADMIN: userRole = UserRole.ADMIN;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userRoleJpa );
        }

        return userRole;
    }

    protected UserRoleJpa userRoleToUserRoleJpa(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRoleJpa userRoleJpa;

        switch ( userRole ) {
            case MEMBER: userRoleJpa = UserRoleJpa.MEMBER;
            break;
            case ADMIN: userRoleJpa = UserRoleJpa.ADMIN;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userRole );
        }

        return userRoleJpa;
    }
}
