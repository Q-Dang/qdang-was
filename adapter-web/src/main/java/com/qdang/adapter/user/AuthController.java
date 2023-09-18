package com.qdang.adapter.user;

import com.qdang.adapter.user.response.TokenResponse;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.LogoutUseCase;
import com.qdang.application.user.port.in.RefreshTokenUseCase;
import com.qdang.global.argument.AuthUser;
import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.http.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.user.request.LoginRequest;
import com.qdang.adapter.user.request.SignUpRequest;
import com.qdang.application.user.port.in.LoginUseCase;
import com.qdang.application.user.port.in.SignUpUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@V1
@WebAdapter
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth API Document")
public class AuthController {

	private final SignUpUseCase signUpUseCase;
	private final LoginUseCase loginUseCase;
	private final LogoutUseCase logoutUseCase;
	private final RefreshTokenUseCase refreshTokenUseCase;

	private final HeaderTokenExtractor headerTokenExtractor;

	@Operation(summary = "기본 유저 회원가입")
	@ApiResponse(
			responseCode = "201",
			description = "회원가입 성공")
	@PostMapping("/signup")
	public ResponseEntity<TokenResponse> singUp(@Valid @RequestBody SignUpRequest request) {
		TokenResponse response =
				TokenResponse.from(
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
	public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
		TokenResponse response =
				TokenResponse.from(
						loginUseCase.login(request.toLoginInfo()));
		return HttpResponse.success(
				SuccessType.LOGIN_SUCCESS,
				response);
	}

	@Operation(summary = "토큰 갱신", description = "Access Token 갱신")
	@ApiResponse(
			responseCode = "200",
			description = "토큰 갱신 성공")
	@PostMapping("/refresh")
	public ResponseEntity<TokenResponse> refresh(
			@Parameter(description = "bearer token in header")
			@RequestHeader(value = "Refresh-Token") String refreshToken,
			HttpServletRequest request
	) {
		TokenResponse response =
				TokenResponse.from(
						refreshTokenUseCase.refreshToken(
								headerTokenExtractor.extractRefreshToken(request)));
		return HttpResponse.success(
				SuccessType.REFRESH_TOKEN_SUCCESS,
				response);
	}

	@Operation(summary = "로그아웃")
	@ApiResponse(
			responseCode = "204",
			description = "로그아웃 성공")
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(
			@AuthUser User user
	) {
		logoutUseCase.logout(user.getId());
		return HttpResponse.success(SuccessType.LOGOUT_SUCCESS);
	}
}