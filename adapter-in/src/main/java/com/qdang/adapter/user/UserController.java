package com.qdang.adapter.user;

import com.qdang.global.resolver.UserId;
import com.qdang.global.response.FailResponse;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.user.request.UpdateUserProfileRequest;
import com.qdang.adapter.user.response.CheckValidationUsernameResponse;
import com.qdang.application.user.port.in.CheckValidationUsernameUseCase;
import com.qdang.application.user.port.in.UpdateUserProfileUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "User", description = "User API Document")
public class UserController {

	private final CheckValidationUsernameUseCase checkValidationUserNameUseCase;
	private final UpdateUserProfileUseCase updateUserProfileUseCase;


	@Operation(summary = "닉네임 중복 확인")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "닉네임 중복 확인 성공",
			content = @Content(schema = @Schema(implementation = CheckValidationUsernameResponse.class))),
		@ApiResponse(
			responseCode = "400",
			description = "잘못된 요청입니다.",
			content = @Content(schema = @Schema(implementation = FailResponse.class))),
		@ApiResponse(
			responseCode = "500",
			description = "알 수 없는 서버 에러가 발생했습니다.",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@GetMapping("/validation/username")
	public ResponseEntity<?> checkValidationUsername(
		@RequestParam("username") String username
	) {
		Boolean isValid = checkValidationUserNameUseCase.checkValidationUsername(username);
		return HttpResponse.success(
			SuccessType.READ_RESOURCE_SUCCESS,
			CheckValidationUsernameResponse.from(isValid));
	}

	@Operation(summary = "프로필 수정")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "204",
			description = "프로필 수정 성공"),
		@ApiResponse(
			responseCode = "400",
			description = "잘못된 요청입니다.",
			content = @Content(schema = @Schema(implementation = FailResponse.class))),
		@ApiResponse(
			responseCode = "500",
			description = "알 수 없는 서버 에러가 발생했습니다.",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PatchMapping("/profile")
	public ResponseEntity<?> updateUserProfile(
		@Parameter(hidden = true) @UserId Long userId,
		@RequestBody UpdateUserProfileRequest request
	) {
		updateUserProfileUseCase.updateUserProfile(request.toUpdateUserProfileCommand(userId));
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}
}