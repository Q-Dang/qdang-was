package com.qdang.adapter.noticeboard;

import com.qdang.adapter.noticeboard.request.PinNoticeBoardRequest;
import com.qdang.adapter.noticeboard.response.GetNoticeBoardListResponse;
import com.qdang.application.noticeboard.port.in.GetNoticeBoardPinnedListUseCase;
import com.qdang.application.noticeboard.port.in.PinNoticeBoardUseCase;
import com.qdang.application.user.domain.User;
import com.qdang.global.response.HttpResponse;
import com.qdang.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@RequiredArgsConstructor
public class NoticeBoardController implements NoticeBoardWebAdapter {

	private final GetNoticeBoardPinnedListUseCase getNoticeBoardPinnedListUseCase;
	private final PinNoticeBoardUseCase pinNoticeBoardUseCase;

	@Override
	public ResponseEntity<GetNoticeBoardListResponse> getNoticeBoardList(
			User user
	) {
		GetNoticeBoardListResponse response =
				GetNoticeBoardListResponse.from(
						getNoticeBoardPinnedListUseCase.getNoticeBoardPinnedList(user.getId()));
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS,
				response);
	}

	@Override
	public ResponseEntity<Void> pinNoticeBoard(
			User user,
			PinNoticeBoardRequest request) {
		pinNoticeBoardUseCase.pinNoticeBoard(
				request.toPinNoticeBoardCommand(user.getId()));
		return HttpResponse.success(
				SuccessType.UPDATE_RESOURCE_SUCCESS);
	}

/*
	@Operation(summary = "게시판 게시글 조회")
	@ApiResponse(
			responseCode = "200",
			description = "게시판 게시글 조회 성공")
	@GetMapping("")
	public ResponseEntity<Void> get1(
	) {
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS);
	}

	@Operation(summary = "게시판 생성")
	@ApiResponse(
			responseCode = "200",
			description = "게시판 생성 성공")
	@PostMapping("")
	public ResponseEntity<Void> get2(
	) {
		return HttpResponse.success(
				SuccessType.READ_RESOURCE_SUCCESS);
	}
 */
}
