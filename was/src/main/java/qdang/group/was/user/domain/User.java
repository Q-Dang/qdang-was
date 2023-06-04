package qdang.group.was.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import qdang.group.was.matchProcess.domain.MatchProcess;
import qdang.group.was.userMatch.domain.UserMatch;

import java.util.List;

@Getter
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