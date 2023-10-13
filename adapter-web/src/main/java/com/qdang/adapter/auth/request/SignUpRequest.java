package com.qdang.adapter.auth.request;

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
    @NotNull(message = "{notNull.auth.loginId}")
    private String loginId;

    @Schema(description = "비밀번호")
    @NotNull(message = "{notNull.auth.password}")
    private String password;

    public SignUpCommand toSignUpCommand() {
        return SignUpCommand.of(loginId, password);
    }
}
