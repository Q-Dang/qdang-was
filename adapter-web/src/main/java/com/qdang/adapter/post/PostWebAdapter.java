package com.qdang.adapter.post;

import com.qdang.global.http.WebAdapter;
import com.qdang.global.pathmatch.V1;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@V1
@WebAdapter(path = "/posts")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Post", description = "Post API Document")
public interface PostWebAdapter {

	/*
	@Operation(summary = "글 쓰기")
	@ApiResponse(
			responseCode = "200",
			description = "글 쓰기 성공")
	@PostMapping("")
	public ResponseEntity<Void> get0();

	@Operation(summary = "내가 쓴 글 조회")
	@ApiResponse(
			responseCode = "200",
			description = "내가 쓴 글 조회 성공")
	@GetMapping("")
	public ResponseEntity<Void> get1();

	@Operation(summary = "댓글 단 글 조회")
	@ApiResponse(
			responseCode = "200",
			description = "댓글 단 글 조회 성공")
	@GetMapping("")
	public ResponseEntity<Void> get2();

	@Operation(summary = "스크랩 한 게시글 조회")
	@ApiResponse(
			responseCode = "200",
			description = "스크랩 한 게시글 조회 성공")
	@GetMapping("")
	public ResponseEntity<Void> get3();

	@Operation(summary = "게시글 스크랩 하기")
	@ApiResponse(
			responseCode = "200",
			description = "게시글 스크랩 하기 성공")
	@PostMapping("")
	public ResponseEntity<Void> get4();

	@Operation(summary = "게시글 좋아요 누르기")
	@ApiResponse(
			responseCode = "200",
			description = "게시글 좋아요 누르기 성공")
	@PostMapping("")
	public ResponseEntity<Void> get5();
	 */
}
