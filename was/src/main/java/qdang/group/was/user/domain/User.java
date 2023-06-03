package qdang.group.was.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long id;

    private String username;
    private String phone;
    private String password;
    private String accessToken;
    private String fcmToken;
    private String profileImage;
    private String birthday;
    private String gender;
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
}