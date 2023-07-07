package qdang.group.was.user.controller.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qdang.group.was.user.service.dto.request.SignUpInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    private String userId;

    private String password;

    private String username;

    private LocalDate birthday;

    private int gender;

    private int proficiency;

    public SignUpInfo newSignUpInfo() {
        return SignUpInfo.of(userId, password, username, birthday, gender, proficiency);
    }
}
