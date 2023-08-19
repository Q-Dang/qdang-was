package com.qdang.adapter.match;

import com.qdang.adapter.match.request.RecordMatchProcessRequest;
import com.qdang.adapter.match.response.StartMatchResponse;
import com.qdang.application.match.domain.Match;
import com.qdang.application.match.port.in.RecordMatchProcessUseCase;
import com.qdang.application.match.port.in.StartMatchUseCase;
import com.qdang.global.adapter.WebAdapter;
import com.qdang.global.pathmatch.V1;
import com.qdang.global.resolver.UserId;
import com.qdang.global.response.FailResponse;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import com.qdang.adapter.match.request.StartMatchRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@V1
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/matches")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Match", description = "Match API Document")
public class MatchController {

	private final StartMatchUseCase startMatchUseCase;
	private final RecordMatchProcessUseCase recordMatchProcessUseCase;

	@Operation(summary = "게임 생성 및 매칭 시작")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "게임 및 전적 생성 성공",
					content = @Content(schema = @Schema(implementation = StartMatchResponse.class))),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping
	public ResponseEntity<?> startMatch(
			@UserId Long userId,
			@RequestBody StartMatchRequest request
	) {
		Match match = startMatchUseCase.startMatch(request.toStartMatchCommand());
		return HttpResponse.success(
				SuccessType.CREATE_RESOURCE_SUCCESS,
				StartMatchResponse.from(match));
	}

	@Operation(summary = "턴 기록")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "204",
					description = "턴 기록 성공"),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청입니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class))),
			@ApiResponse(
					responseCode = "500",
					description = "알 수 없는 서버 에러가 발생했습니다.",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	@PostMapping("/processes")
	public ResponseEntity<?> recordMatchProcess(
			@UserId Long userId,
			@RequestBody RecordMatchProcessRequest request
	) {
		recordMatchProcessUseCase.recordMatchProcess(request.toRecordMatchProcessCommand());
		return HttpResponse.success(SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

}
