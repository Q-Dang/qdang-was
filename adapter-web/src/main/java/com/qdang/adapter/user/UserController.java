package com.qdang.adapter.user;

import com.qdang.adapter.user.response.GetUserMatchHistoryResponse;
import com.qdang.adapter.user.response.GetUserProfileResponse;
import com.qdang.application.match.port.in.GetUserMatchHistoryUseCase;
import com.qdang.application.user.port.in.GetUserProfileUseCase;
import com.qdang.global.adapter.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.resolver.UserId;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.user.request.UpdateUserProfileRequest;
import com.qdang.adapter.user.response.CheckValidationUsernameResponse;
import com.qdang.application.user.port.in.CheckValidationUsernameUseCase;
import com.qdang.application.user.port.in.UpdateUserProfileUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@V1
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/users")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "User", description = "User API Document")
public class UserController {

	private final CheckValidationUsernameUseCase checkValidationUserNameUseCase;
	private final UpdateUserProfileUseCase updateUserProfileUseCase;
	private final GetUserMatchHistoryUseCase getUserMatchHistoryUseCase;
	private final GetUserProfileUseCase getUserProfileUseCase;

	@Operation(summary = "유저 본인 프로필 조회")
	@ApiResponse(
			responseCode = "200",
			description = "유저 본인 프로필 조회 성공")
	@GetMapping("/profiles")
	public ResponseEntity<GetUserProfileResponse> getMyProfile(
			@UserId Long userId
	) {
		/**
		 * 이름
		 * 상태메세지
		 * - 상태메세지 컬럼 추가하기
		 * 사진
		 * - 디폴트 이미지 추가하기
		 */
		GetUserProfileResponse response =
				GetUserProfileResponse.from(
						getUserProfileUseCase.getUserProfile(userId));
		return HttpResponse.success(SuccessType.READ_RESOURCE_SUCCESS, response);
	}


	@Operation(summary = "닉네임 유효성 확인")
	@ApiResponse(
			responseCode = "200",
			description = "닉네임 중복 확인 성공")
	@GetMapping("/validation/username")
	public ResponseEntity<CheckValidationUsernameResponse> checkValidationUsername(
			@RequestParam("username") String username
	) {
		CheckValidationUsernameResponse response =
				CheckValidationUsernameResponse.from(
						checkValidationUserNameUseCase.checkValidationUsername(username));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Operation(summary = "프로필 수정")
	@ApiResponse(
			responseCode = "204",
			description = "프로필 수정 성공")
	@PatchMapping("/profile")
	public ResponseEntity<Void> updateUserProfile(
			@UserId Long userId,
			@Valid @RequestBody UpdateUserProfileRequest request
	) {
		updateUserProfileUseCase.updateUserProfile(request.toUpdateUserProfileCommand(userId));
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

	@Operation(summary = "경기 전적 조회")
	@ApiResponse(
			responseCode = "200",
			description = "경기 전적 조회 성공")
	@GetMapping("/matches")
	public ResponseEntity<GetUserMatchHistoryResponse> getUserMatchHistory(
			@UserId Long userId
	) {
		GetUserMatchHistoryResponse response =
				GetUserMatchHistoryResponse.from(
						getUserMatchHistoryUseCase.getMatchHistoryByUserId(userId));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}
}
