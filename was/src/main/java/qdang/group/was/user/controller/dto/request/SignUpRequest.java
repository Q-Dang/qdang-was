package qdang.group.was.user.controller.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qdang.group.was.user.service.dto.request.SignUpInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    private String password;

    @NotNull
    private LocalDate birthday;

    @NotNull
    @NotEmpty
    private int gender;

    @NotNull
    private int proficiency;

    public SignUpInfo newSignUpInfo() {
        return SignUpInfo.of(username, password, birthday, gender, proficiency);
    }
}
