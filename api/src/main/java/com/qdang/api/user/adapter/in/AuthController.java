package com.qdang.api.user.adapter.in;

import com.qdang.api.user.adapter.in.request.LoginRequest;
import com.qdang.api.user.adapter.in.request.SignUpRequest;
import com.qdang.api.user.service.dto.response.TokenCollection;
import com.qdang.api.user.adapter.in.response.LoginResponse;
import com.qdang.global.response.ApiResponse;
import com.qdang.global.response.SuccessType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qdang.api.user.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
//@CrossOrigin
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
		TokenCollection jwtToken = authService.login(request.newLoginInfo());
		return ApiResponse.success(SuccessType.LOGIN_SUCCESS, LoginResponse.from(jwtToken));
	}

}
