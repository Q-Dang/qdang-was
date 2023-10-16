package com.qdang.adapter.user;

import com.qdang.adapter.user.response.GetUserMatchHistoryResponse;
import com.qdang.adapter.user.response.GetUserProfileResponse;
import com.qdang.adapter.user.response.SearchUserResponse;
import com.qdang.application.match.port.in.GetUserMatchHistoryUseCase;
import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.in.GetUserProfileUseCase;
import com.qdang.application.user.port.in.SearchUserByUsernameUseCase;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.user.request.UpdateUserProfileRequest;
import com.qdang.adapter.user.response.UserValidationResponse;
import com.qdang.application.user.port.in.CheckValidationUsernameUseCase;
import com.qdang.application.user.port.in.UpdateUserProfileUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@RequiredArgsConstructor
public class UserController implements UserWebAdapter {

	private final CheckValidationUsernameUseCase checkValidationUserNameUseCase;
	private final UpdateUserProfileUseCase updateUserProfileUseCase;
	private final GetUserMatchHistoryUseCase getUserMatchHistoryUseCase;
	private final GetUserProfileUseCase getUserProfileUseCase;
	private final SearchUserByUsernameUseCase searchUserByUsernameUseCase;

	@Override
	public ResponseEntity<GetUserProfileResponse> getMyProfile(
			User user
	) {
		GetUserProfileResponse response =
				GetUserProfileResponse.from(
						getUserProfileUseCase.getUserProfile(user.getId()));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<SearchUserResponse> searchUserByUsername(
			String username
	) {
		SearchUserResponse response =
				SearchUserResponse.from(
						searchUserByUsernameUseCase.searchUserByUsername(username));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<UserValidationResponse> checkValidationUsername(
			String username
	) {
		UserValidationResponse response =
				UserValidationResponse.from(
						checkValidationUserNameUseCase.checkValidationUsername(username));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<Void> updateUserProfile(
			User user,
			UpdateUserProfileRequest request
	) {
		updateUserProfileUseCase.updateUserProfile(
				request.toUpdateUserProfileCommand(user.getId()));
		return HttpResponse.success(
				SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

	@Override
	public ResponseEntity<GetUserMatchHistoryResponse> getUserMatchHistory(
			User user
	) {
		GetUserMatchHistoryResponse response =
				GetUserMatchHistoryResponse.from(
						getUserMatchHistoryUseCase.getMatchHistoryByUserId(user.getId()));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_LIST_SUCCESS,
				response);
	}
}
