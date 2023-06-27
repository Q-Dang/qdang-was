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
import qdang.group.was.user.controller.dto.request.LoginRequest;
import qdang.group.was.user.controller.dto.request.SignUpRequest;
import qdang.group.was.user.controller.dto.response.LoginResponse;
import qdang.group.was.user.service.AuthService;
import qdang.group.was.user.service.dto.response.JwtToken;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth API Document")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	@Operation(summary = "회원가입", description = "기본 유저 회원가입 api")
	public ResponseEntity<?> singUp(@RequestBody SignUpRequest request) {
		authService.signUp(request.newSignUpInfo());
		return ApiResponse.success(SuccessType.SIGNUP_SUCCESS);
	}

	@PostMapping("/login")
	@Operation(summary = "로그인", description = "기본 유저 로그인")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		JwtToken jwtToken = authService.login(request.newLoginInfo());
		return ApiResponse.success(SuccessType.LOGIN_SUCCESS, LoginResponse.from(jwtToken));
	}
}
