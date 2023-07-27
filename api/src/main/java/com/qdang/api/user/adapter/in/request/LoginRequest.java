package com.qdang.api.user.adapter.in.request;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.qdang.api.user.service.dto.request.LoginInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    public LoginInfo newLoginInfo() {
        return LoginInfo.of(userId, password);
    }
}
