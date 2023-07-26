package qdang.group.was.domain.user.controller.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qdang.group.was.domain.user.service.dto.request.LoginInfo;

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
