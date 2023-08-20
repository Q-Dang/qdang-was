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
		 * 실력 분포도
		 * - 컬럼 추가하기
		 *      - 에버리지
		 *      	- 에버리지 컬럼 추가
		 *      	- 경기 종료 시
		 *      		- 유저의 에버리지 = 경기수 x 에버리지 + 경기 에버리지 / (경기수 + 1)
		 *      - 판수
		 *      	- 경기 수 컬럼 추가
		 *      	- 경기 종류할 때
		 *      		- 경기 수 + 1
		 *      - 하이런
		 *      	- 하이런 컬럼 추가
		 *      	- 경기 종료 시 max high run 으로 비교해서 값 넣기
		 *      - 타율
		 *      	- 총 이닝 수 , 총 성공한 이닝 수, 총 실패한 이닝 수 컬럼 추가
		 *      	- 공타율 컬럼 추가
		 *      	- 경기 종료 시
		 *      		- 유저 경기 정보에서
		 *      			- 이닝 수 = 총 이능 수 + 이닝 수
		 *      			- 총 실패한 이닝 수 = 총 실패한 이닝 수 + 실패한 이닝 수
		 *      			- 총 성공한 이닝 수 = 총 성공한 이닝 수 + 성공한 이닝 수
		 *      			- 경기의 타율 = 성공한 이닝 수 / 이닝 수
		 *      			- 유저의 공타율 = 총 성공한 이닝 수 / 총 이닝 수
		 *      - 장타율
		 *      	- 총 장타 횟수 컬럼 추가, 장타율 컬럼 추가하기
		 *			- 경기 종료 시
		 *				- 경기 ID + player id => 경기 프로세스
		 *				- 경기 프로세스id + 유저 id +  현재 장타가 5인  => 유저 경기 프로세스
		 *				- 총 장타 횟수 = 총 장타 횟수 + 위 유저 경기 프로세스 횟수
		 *				- 유저의 장타율 = 총 장타 횟수 /총 이닝 수
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
