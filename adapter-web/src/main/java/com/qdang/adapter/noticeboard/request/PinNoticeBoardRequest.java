package com.qdang.adapter.noticeboard.request;

import com.qdang.application.noticeboard.port.in.command.PinNoticeBoardCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시판 상단 고정하기 요청")
public class PinNoticeBoardRequest {

	@Schema(description = "게시판 아이디", example = "1")
	@NotNull(message = "{notNull.noticeBoard.noticeBoardId}")
	private Long noticeBoardId;

	@Schema(description = "고정 여부", example = "true")
	@NotNull(message = "{notNull.noticeBoard.pin}")
	private Boolean pin;

	public PinNoticeBoardCommand toPinNoticeBoardCommand(Long userId) {
		return PinNoticeBoardCommand.of(
				userId,
				noticeBoardId,
				pin);
	}
}