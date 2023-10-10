package com.qdang.adapter.user;

import com.qdang.adapter.user.request.UpdateUserProfileRequest;
import com.qdang.adapter.user.response.GetUserMatchHistoryResponse;
import com.qdang.adapter.user.response.GetUserProfileResponse;
import com.qdang.adapter.user.response.SearchUserResponse;
import com.qdang.adapter.user.response.UserValidationResponse;
import com.qdang.application.user.domain.User;
import com.qdang.global.argument.AuthUser;
import com.qdang.global.pathmatch.V1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@V1
@RequestMapping("/users")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "User", description = "User API Document")
public interface UserWebAdapter {

	@Operation(summary = "내 프로필 조회")
	@ApiResponse(
			responseCode = "200",
			description = "내 프로필 조회 성공")
	@GetMapping("/profiles")
	ResponseEntity<GetUserProfileResponse> getMyProfile(
			@AuthUser User user
	);

	@Operation(summary = "유저 이름으로 프로필 조회")
	@ApiResponse(
			responseCode = "200",
			description = "유저 이름으로 프로필 조회 성공")
	@GetMapping("/search")
	ResponseEntity<SearchUserResponse> searchUserByUsername(
			@RequestParam(value = "username", required = false) String username
	);

	@Operation(summary = "닉네임 유효성 확인")
	@ApiResponse(
			responseCode = "200",
			description = "닉네임 유효성 확인 성공")
	@GetMapping("/validation/username")
	ResponseEntity<UserValidationResponse> checkValidationUsername(
			@RequestParam("username") String username
	);

	@Operation(summary = "프로필 수정")
	@ApiResponse(
			responseCode = "204",
			description = "프로필 수정 성공")
	@PatchMapping("/profile")
	ResponseEntity<Void> updateUserProfile(
			@AuthUser User user,
			@Valid @RequestBody UpdateUserProfileRequest request
	);

	@Operation(summary = "내 경기 전적 조회")
	@ApiResponse(
			responseCode = "200",
			description = "내 경기 전적 조회 성공")
	@GetMapping("/matches")
	ResponseEntity<GetUserMatchHistoryResponse> getUserMatchHistory(
			@AuthUser User user
	);
}
