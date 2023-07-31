package com.qdang.adapter.match;

import com.qdang.adapter.global.response.HttpResponse;
import com.qdang.adapter.global.response.SuccessType;
import com.qdang.adapter.match.request.StartMatchRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matches")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Match", description = "Match API Document")
public class MatchController {

	@PostMapping
	public ResponseEntity<?> startMatch(
		@RequestBody StartMatchRequest request
	) {
		request.toStartMatchCommand();
		return HttpResponse.success(SuccessType.CREATE_RESOURCE_SUCCESS);
	}
}
