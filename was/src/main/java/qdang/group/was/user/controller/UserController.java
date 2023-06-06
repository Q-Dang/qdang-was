package qdang.group.was.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdang.group.was.global.response.ApiResponse;
import qdang.group.was.global.response.SuccessType;
import qdang.group.was.user.controller.dto.request.PostLoginRequest;
import qdang.group.was.user.controller.dto.request.SignUpRequest;
import qdang.group.was.user.service.UserService;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API Document")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "기본 유저 회원가입 api")
    public ResponseEntity<?> singUp(@RequestBody SignUpRequest request) {
        userService.signUp(request.newSignUpInfo());
        return ApiResponse.success(SuccessType.SIGNUP_SUCCESS);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "기본 유저 로그인")
    public ResponseEntity<?> login(PostLoginRequest request) {
        userService.login(request.newLoginRequest());
        return ApiResponse.success(SuccessType.LOGIN_SUCCESS);
    }
}
