package com.qdang.adapter.post;

import com.qdang.adapter.post.request.CommentPostRequest;
import com.qdang.adapter.post.request.LikePostRequest;
import com.qdang.adapter.post.request.ScrapPostRequest;
import com.qdang.adapter.post.request.WritePostRequest;
import com.qdang.adapter.post.response.GetPostDetailResponse;
import com.qdang.adapter.post.response.SearchPostResponse;
import com.qdang.application.user.domain.User;
import com.qdang.global.argument.AuthUser;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/posts")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Post", description = "Post API Document")
public interface PostWebAdapter {

	@Operation(summary = "게시글 상세 조회")
	@ApiResponse(
			responseCode = "200",
			description = "게시글 상세 조회 성공")
	@GetMapping("/{postId}")
	ResponseEntity<GetPostDetailResponse> getPostDetail(
			@AuthUser User user,
			@PathVariable Long postId
	);

	@Operation(summary = "게시글 쓰기")
	@ApiResponse(
			responseCode = "201",
			description = "게시글 쓰기 성공")
	@PostMapping
	ResponseEntity<Void> writePost(
			@AuthUser User user,
			@Valid @RequestBody WritePostRequest request
	);

	@Operation(summary = "게시글 스크랩하기, 취소하기")
	@ApiResponse(
			responseCode = "204",
			description = "게시글 스크랩하기, 취소하기 성공")
	@PostMapping("/scrap")
	ResponseEntity<Void> scrapPost(
			@AuthUser User user,
			@Valid @RequestBody ScrapPostRequest request
	);

	@Operation(summary = "게시글 좋아요하기, 취소하기")
	@ApiResponse(
			responseCode = "204",
			description = "게시글 좋아요하기, 취소하기 성공")
	@PostMapping("/likes")
	ResponseEntity<Void> likePost(
			@AuthUser User user,
			@Valid @RequestBody LikePostRequest request
	);

	@Operation(summary = "게시글 댓글 달기")
	@ApiResponse(
			responseCode = "201",
			description = "게시글 댓글 달기 성공")
	@PostMapping("/{postId}/comment")
	ResponseEntity<Void> commentPost(
			@AuthUser User user,
			@Valid @RequestBody CommentPostRequest request
	);

	@Operation(summary = "게시글 검색하기")
	@ApiResponse(
			responseCode = "200",
			description = "게시글 검색하기 성공")
	@GetMapping("/search")
	ResponseEntity<SearchPostResponse> searchPost(
			@RequestParam(value = "keyword") String keyword
	);

	/*
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
	 */
}
