package com.qdang.adapter.match;

import com.qdang.adapter.match.request.QuitMatchRequest;
import com.qdang.adapter.match.request.RecordMatchProcessRequest;
import com.qdang.adapter.match.response.GetMatchInfoResponse;
import com.qdang.adapter.match.response.StartMatchResponse;
import com.qdang.application.match.port.in.GetMatchDetailInfoUseCase;
import com.qdang.application.match.port.in.QuitGameUseCase;
import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.StartMatchUseCase;
import com.qdang.application.user.domain.User;
import com.qdang.global.http.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.match.request.StartMatchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@V1
@WebAdapter
@RequiredArgsConstructor
class MatchController implements MatchWebAdapter {

	private final GetMatchDetailInfoUseCase getMatchDetailInfoUseCase;
	private final StartMatchUseCase startMatchUseCase;
	private final RecordMatchProcessUseCase recordMatchProcessUseCase;
	private final QuitGameUseCase quitGameUseCase;

	@Override
	public ResponseEntity<GetMatchInfoResponse> getMatchInfo(
			Long matchId,
			Long userId
	) {
		GetMatchInfoResponse response =
				GetMatchInfoResponse.of(
						userId,
						getMatchDetailInfoUseCase.getMatchDetailInfoByMatchId(matchId));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<StartMatchResponse> startMatch(
			StartMatchRequest request
	) {
		StartMatchResponse response =
				StartMatchResponse.from(
						startMatchUseCase.startMatch(request.toStartMatchCommand()));
		return HttpResponse.success(
				SuccessType.CREATE_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<Void> recordMatchProcess(
			RecordMatchProcessRequest request
	) {
		recordMatchProcessUseCase.recordMatchProcess(request.toRecordMatchProcessCommand());
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

	@Override
	public ResponseEntity<Void> quitMatch(
			User user,
			QuitMatchRequest request
	) {
		quitGameUseCase.quitGame(request.toQuitMatchCommand(user.getId()));
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}
}