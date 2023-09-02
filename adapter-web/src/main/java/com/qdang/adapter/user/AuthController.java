package com.qdang.adapter.user;

import com.qdang.global.adapter.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.user.request.LoginRequest;
import com.qdang.adapter.user.request.SignUpRequest;
import com.qdang.adapter.user.response.LoginResponse;
import com.qdang.adapter.user.response.SignUpResponse;
import com.qdang.application.user.domain.TokenCollection;
import com.qdang.application.user.port.in.LoginUseCase;
import com.qdang.application.user.port.in.SignUpUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@V1
@WebAdapter
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth API Document")
public class AuthController {

	private final SignUpUseCase signUpUseCase;
	private final LoginUseCase loginUseCase;

	@Operation(summary = "기본 유저 회원가입")
	@ApiResponse(
			responseCode = "201",
			description = "회원가입 성공")
	@PostMapping("/signup")
	public ResponseEntity<SignUpResponse> singUp(@Valid @RequestBody SignUpRequest request) {
		SignUpResponse response =
				SignUpResponse.from(
						signUpUseCase.signUp(request.toSignUpCommand()));
		return HttpResponse.success(
				SuccessType.SIGNUP_SUCCESS,
				response);
	}

	@Operation(summary = "로그인", description = "기본 유저 로그인")
	@ApiResponse(
			responseCode = "200",
			description = "로그인 성공")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		LoginResponse response =
				LoginResponse.from(
						loginUseCase.login(request.toLoginInfo()));
		return HttpResponse.success(
				SuccessType.LOGIN_SUCCESS,
				response);
	}

}
