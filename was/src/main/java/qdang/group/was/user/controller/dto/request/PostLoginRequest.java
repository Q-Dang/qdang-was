package qdang.group.was.user.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qdang.group.was.user.service.dto.request.LoginInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostLoginRequest {

    public LoginInfo newLoginRequest() {
        return LoginInfo.builder().build();
    }
}
