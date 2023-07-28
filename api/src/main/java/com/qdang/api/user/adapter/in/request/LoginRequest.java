package com.qdang.api.user.adapter.in.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.qdang.api.user.application.port.in.command.LoginCommand;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @Schema(description = "로그인 아이디")
    @NotNull
    private String loginId;

    @NotNull
    @Schema(description = "비밀번호")
    private String password;

    public LoginCommand toLoginInfo() {
        return LoginCommand.of(loginId, password);
    }
}
