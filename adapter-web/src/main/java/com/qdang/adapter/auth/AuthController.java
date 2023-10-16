package com.qdang.adapter.auth;

import com.qdang.adapter.auth.response.TokenResponse;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.LogoutUseCase;
import com.qdang.application.user.port.in.RefreshTokenUseCase;
import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.http.WebAdapter;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.auth.request.LoginRequest;
import com.qdang.adapter.auth.request.SignUpRequest;
import com.qdang.application.user.port.in.LoginUseCase;
import com.qdang.application.user.port.in.SignUpUseCase;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@WebAdapter(path = "/auth")
@RequiredArgsConstructor
public class AuthController implements AuthAdapter {

	private final SignUpUseCase signUpUseCase;
	private final LoginUseCase loginUseCase;
	private final LogoutUseCase logoutUseCase;
	private final RefreshTokenUseCase refreshTokenUseCase;

	private final HeaderTokenExtractor headerTokenExtractor;

	@Override
	public ResponseEntity<TokenResponse> singUp(
			SignUpRequest request
	) {
		TokenResponse response =
				TokenResponse.from(
						signUpUseCase.signUp(request.toSignUpCommand()));
		return HttpResponse.success(
				SuccessType.SIGNUP_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<TokenResponse> login(
			LoginRequest request
	) {
		TokenResponse response =
				TokenResponse.from(
						loginUseCase.login(request.toLoginInfo()));
		return HttpResponse.success(
				SuccessType.LOGIN_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<TokenResponse> refresh(
			String refreshToken,
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

	@Override
	public ResponseEntity<Void> logout(
			User user
	) {
		logoutUseCase.logout(user.getId());
		return HttpResponse.success(SuccessType.LOGOUT_SUCCESS);
	}
}