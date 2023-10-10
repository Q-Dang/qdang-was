package com.qdang.adapter.auth.request;

import com.qdang.application.user.port.in.command.LoginCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "로그인 요청")
public class LoginRequest {

    @Schema(description = "로그인 아이디")
    @NotNull(message = "{auth.loginId.notNull}")
    private String loginId;

    @Schema(description = "비밀번호")
    @NotNull(message = "{auth.password.notNull}")
    private String password;

    public LoginCommand toLoginInfo() {
        return LoginCommand.of(loginId, password);
    }
}
