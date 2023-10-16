package com.qdang.adapter.match;

import com.qdang.adapter.match.request.QuitMatchRequest;
import com.qdang.adapter.match.request.RecordMatchProcessRequest;
import com.qdang.adapter.match.request.StartMatchRequest;
import com.qdang.adapter.match.response.GetMatchInfoResponse;
import com.qdang.adapter.match.response.StartMatchResponse;
import com.qdang.application.user.domain.User;
import com.qdang.global.argument.AuthUser;
import com.qdang.global.http.WebAdapter;
import com.qdang.global.pathmatch.V1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@V1
@WebAdapter(path = "/matches")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Match", description = "Match API Document")
public interface MatchWebAdapter {

	@Operation(summary = "게임 상세 조회")
	@ApiResponse(
			responseCode = "200",
			description = "게임 상세 조회 성공")
	@GetMapping("/{matchId}/users/{userId}")
	ResponseEntity<GetMatchInfoResponse> getMatchInfo(
			@PathVariable Long matchId,
			@PathVariable Long userId
	);

	@Operation(summary = "게임 생성 및 매칭 시작")
	@ApiResponse(
			responseCode = "201",
			description = "게임 및 전적 생성 성공")
	@PostMapping
	ResponseEntity<StartMatchResponse> startMatch(
			@Valid @RequestBody StartMatchRequest request
	);

	@Operation(summary = "턴 기록")
	@ApiResponse(
			responseCode = "204",
			description = "턴 기록 성공")
	@PostMapping("/processes")
	ResponseEntity<Void> recordMatchProcess(
			@Valid @RequestBody RecordMatchProcessRequest request
	);

	@Operation(summary = "경기 종료")
	@ApiResponse(
			responseCode = "204",
			description = "경기 종료 성공")
	@PostMapping("/quit")
	ResponseEntity<Void> quitMatch(
			@AuthUser User user,
			@Valid @RequestBody QuitMatchRequest request
	);
}

