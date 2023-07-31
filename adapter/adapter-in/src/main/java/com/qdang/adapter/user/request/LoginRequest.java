package com.qdang.adapter.user.request;

import com.qdang.application.user.port.in.command.LoginCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
