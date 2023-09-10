package com.qdang.adapter.match;

import com.qdang.adapter.match.request.QuitMatchRequest;
import com.qdang.adapter.match.request.RecordMatchProcessRequest;
import com.qdang.adapter.match.response.GetMatchInfoRequest;
import com.qdang.adapter.match.response.StartMatchResponse;
import com.qdang.application.match.port.in.GetMatchUseCase;
import com.qdang.application.match.port.in.QuitGameUseCase;
import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.StartMatchUseCase;
import com.qdang.application.user.domain.User;
import com.qdang.global.http.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.argument.LoginUser;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.match.request.StartMatchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@V1
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/matches")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Match", description = "Match API Document")
public class MatchController {

	private final GetMatchUseCase getMatchUseCase;
	private final StartMatchUseCase startMatchUseCase;
	private final RecordMatchProcessUseCase recordMatchProcessUseCase;
	private final QuitGameUseCase quitGameUseCase;

	@Operation(summary = "게임 상세 조회")
	@ApiResponse(
			responseCode = "200",
			description = "게임 상세 조회 성공")
	@GetMapping("/{matchId}")
	public ResponseEntity<GetMatchInfoRequest> getMatchInfo(
			@PathVariable Long matchId
	) {
		GetMatchInfoRequest response =
				GetMatchInfoRequest.from(
						getMatchUseCase.getMatchByMatchId(matchId));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Operation(summary = "게임 생성 및 매칭 시작")
	@ApiResponse(
			responseCode = "201",
			description = "게임 및 전적 생성 성공")
	@PostMapping
	public ResponseEntity<StartMatchResponse> startMatch(
			@Valid @RequestBody StartMatchRequest request
	) {
		StartMatchResponse response =
				StartMatchResponse.from(
						startMatchUseCase.startMatch(request.toStartMatchCommand()));
		return HttpResponse.success(
				SuccessType.CREATE_RESOURCE_SUCCESS,
				response);
	}

	@Operation(summary = "턴 기록")
	@ApiResponse(
			responseCode = "204",
			description = "턴 기록 성공")
	@PostMapping("/processes")
	public ResponseEntity<Void> recordMatchProcess(
			@Valid @RequestBody RecordMatchProcessRequest request
	) {
		recordMatchProcessUseCase.recordMatchProcess(request.toRecordMatchProcessCommand());
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

	@Operation(summary = "경기 종료")
	@ApiResponse(
			responseCode = "204",
			description = "경기 종료 성공")
	@PostMapping("/quit")
	public ResponseEntity<Void> quitMatch(
			@LoginUser User user,
			@Valid @RequestBody QuitMatchRequest request
	) {
		quitGameUseCase.quitGame(request.toQuitMatchCommand(user.getId()));
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}
}