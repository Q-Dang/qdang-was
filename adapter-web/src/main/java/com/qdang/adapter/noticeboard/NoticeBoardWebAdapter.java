package com.qdang.adapter.noticeboard;

import com.qdang.adapter.noticeboard.request.PinNoticeBoardRequest;
import com.qdang.adapter.noticeboard.response.GetNoticeBoardListResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@V1
@RequestMapping("/notice-boards")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Notice Board", description = "Notice Board API Document")
public interface NoticeBoardWebAdapter {

	@Operation(summary = "게시판 목록 조회")
	@ApiResponse(
			responseCode = "200",
			description = "게시판 목록 조회 성공")
	@GetMapping()
	ResponseEntity<GetNoticeBoardListResponse> getNoticeBoardList(
			@AuthUser User user
	);

	@Operation(summary = "게시판 상단 고정하기")
	@ApiResponse(
			responseCode = "204",
			description = "게시판 상단 고정하기 성공")
	@PostMapping("/pin")
	ResponseEntity<Void> pinNoticeBoard(
			@AuthUser User user,
			@Valid @RequestBody PinNoticeBoardRequest request
	);
}
