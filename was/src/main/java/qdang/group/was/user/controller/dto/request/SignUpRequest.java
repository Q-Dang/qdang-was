package qdang.group.was.user.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qdang.group.was.user.service.dto.request.SignUpRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSignUpRequest {

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

    public SignUpRequest newSignUpRequest() {
        return SignUpRequest.builder().build();
    }
}
