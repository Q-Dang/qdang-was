package qdang.group.was.domain.user.controller.dto.request;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qdang.group.was.domain.user.service.dto.request.SignUpInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    private String userId;

    private String password;

    private String username;

    private LocalDate birthday;

    private String gender;

    private int proficiency;

    public SignUpInfo newSignUpInfo() {
        return SignUpInfo.of(userId, password, username, birthday, gender, proficiency);
    }
}
