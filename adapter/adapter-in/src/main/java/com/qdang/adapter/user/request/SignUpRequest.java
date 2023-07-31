package com.qdang.adapter.user.request;

import com.qdang.application.user.port.in.command.SignUpCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    @Schema(description = "로그인 아이디")
    private String loginId;

    @Schema(description = "비밀번호")
    private String password;

    public SignUpCommand toSignUpCommand() {
        return SignUpCommand.of(loginId, password);
    }
}
