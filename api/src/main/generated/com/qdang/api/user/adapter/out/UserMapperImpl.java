package com.qdang.api.user.adapter.out;

import com.qdang.api.user.domain.User;
import com.qdang.core.user.UserJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-28T10:44:33+0900",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserJpaEntity mapToJpaEntity(User arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserJpaEntity.UserJpaEntityBuilder userJpaEntity = UserJpaEntity.builder();

        userJpaEntity.id( arg0.getId() );
        userJpaEntity.userRole( arg0.getUserRole() );
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

    @Override
    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        if ( userJpaEntity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userJpaEntity.getId() );
        user.userRole( userJpaEntity.getUserRole() );
        user.loginId( userJpaEntity.getLoginId() );
        user.password( userJpaEntity.getPassword() );
        user.username( userJpaEntity.getUsername() );
        user.birthday( userJpaEntity.getBirthday() );
        user.gender( userJpaEntity.getGender() );
        user.proficiency( userJpaEntity.getProficiency() );
        user.phone( userJpaEntity.getPhone() );
        user.fcmToken( userJpaEntity.getFcmToken() );
        user.profileImage( userJpaEntity.getProfileImage() );
        user.address( userJpaEntity.getAddress() );
        user.detailAddress( userJpaEntity.getDetailAddress() );
        user.isResting( userJpaEntity.getIsResting() );
        user.isLeaving( userJpaEntity.getIsLeaving() );
        user.phoneAuthCode( userJpaEntity.getPhoneAuthCode() );
        user.phoneAuthAt( userJpaEntity.getPhoneAuthAt() );
        user.isPhoneAuth( userJpaEntity.getIsPhoneAuth() );
        user.joinAt( userJpaEntity.getJoinAt() );
        user.joinAgree( userJpaEntity.getJoinAgree() );
        user.agreeUpdateAt( userJpaEntity.getAgreeUpdateAt() );
        user.accessAt( userJpaEntity.getAccessAt() );
        user.accessCount( userJpaEntity.getAccessCount() );

        return user.build();
    }
}
