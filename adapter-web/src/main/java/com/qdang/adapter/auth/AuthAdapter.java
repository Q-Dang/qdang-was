package com.qdang.adapter.auth;

import com.qdang.adapter.auth.request.LoginRequest;
import com.qdang.adapter.auth.request.SignUpRequest;
import com.qdang.adapter.auth.response.TokenResponse;
import com.qdang.application.user.domain.User;
import com.qdang.global.argument.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Auth", description = "Auth API Document")
public interface AuthAdapter {

	@Operation(summary = "기본 유저 회원가입")
	@ApiResponse(
			responseCode = "201",
			description = "회원가입 성공")
	@PostMapping("/signup")
	ResponseEntity<TokenResponse> singUp(
			@Valid @RequestBody SignUpRequest request
	);

	@Operation(summary = "로그인", description = "기본 유저 로그인")
	@ApiResponse(
			responseCode = "200",
			description = "로그인 성공")
	@PostMapping("/login")
	ResponseEntity<TokenResponse> login(
			@Valid @RequestBody LoginRequest request
	);

	@Operation(summary = "토큰 갱신", description = "Access Token 갱신")
	@ApiResponse(
			responseCode = "200",
			description = "토큰 갱신 성공")
	@PostMapping("/refresh")
	ResponseEntity<TokenResponse> refresh(
			@Parameter(description = "bearer token in header")
			@RequestHeader(value = "Refresh-Token") String refreshToken,
			HttpServletRequest request
	);

	@Operation(summary = "로그아웃")
	@ApiResponse(
			responseCode = "204",
			description = "로그아웃 성공")
	@PostMapping("/logout")
	ResponseEntity<Void> logout(
			@AuthUser User user
	);
}
