package com.qdang.adapter.user.request;

import com.qdang.application.user.port.in.command.SignUpCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원가입 요청")
public class SignUpRequest {

    @Schema(description = "로그인 아이디")
    @NotNull(message = "{auth.loginId.notNull}")
    private String loginId;

    @Schema(description = "비밀번호")
    @NotNull(message = "{auth.password.notNull}")
    private String password;

    public SignUpCommand toSignUpCommand() {
        return SignUpCommand.of(loginId, password);
    }
}
